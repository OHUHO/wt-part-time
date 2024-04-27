<template>
    <div class="app-container">
        <div class="container">
            <el-tabs v-model="tabName">
                <el-tab-pane :label="`待审核(${businessesPending.length})`" name="pending">
                    <el-table :data="businessesPending" :show-header="false" style="width: 100%">
                        <el-table-column width="auto">
                            <template slot-scope="scope">
                                <span class="message-title" @click='details(scope.row.businessId,false)'>
                                    <h4 style="cursor: pointer;">{{scope.row.createUserName}} 提交的入驻「{{scope.row.storeName}}」店铺的申请</h4>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="createTime" width="160"></el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane :label="`已审核(${businessesDone.length})`" name="done">
                    <el-table :data="businessesDone" :show-header="false" style="width: 100%">
                        <el-table-column>
                            <template slot-scope="scope">
                                <span class="message-title" @click='details(scope.row.businessId,true)'>
                                    <h4 style="cursor: pointer;">
                                        {{scope.row.createUserName}} 提交的入驻「{{scope.row.storeName}}」店铺的申请
                                        <el-tag type="success" size="mini" v-if="scope.row.status == 2">已通过</el-tag>
                                        <el-tag type="danger" size="mini" v-else>未通过</el-tag>
                                    </h4>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="createTime" width="180"></el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!-- 审核弹框 -->
        <el-dialog
            title="商家资质审核"
            :visible.sync="dialogVisible"
            :close-on-click-modal="false"
            width="60%"
            center>
            <el-descriptions title="商家信息">
                <el-descriptions-item label="姓名"> {{ business.username }} </el-descriptions-item>
                <el-descriptions-item label="性别">
                    {{ business.gender === '1' ? '男' : business.gender === '2' ? '女' : '未知' }}
                </el-descriptions-item>
                <el-descriptions-item label="手机号" >{{ business.phone }}</el-descriptions-item>
                <el-descriptions-item label="身份证号">{{ business.cardNo }}</el-descriptions-item>
                <el-descriptions-item label="店铺名称">{{ business.storeName }}</el-descriptions-item>
                <el-descriptions-item label="店铺类型">{{ business.storeType }}</el-descriptions-item>
                <el-descriptions-item label="证件类型">{{ business.papersType }}</el-descriptions-item>
                <el-descriptions-item label="证件号码">{{ business.papersNumber }}</el-descriptions-item>
                <el-descriptions-item label="申请时间">{{ business.createTime }}</el-descriptions-item>
                <el-descriptions-item label="申请人">{{ business.createUserName }}</el-descriptions-item>
                <el-descriptions-item label="审核状态">
                    <!-- {{ business.status === 1 ? '审核中' : business.status === 2 ? '已通过' : '未通过' }} -->
                    <el-tag type="info" size="mini" v-if="business.status == 1">审核中</el-tag>
                    <el-tag type="success" size="mini" v-else-if="business.status == 2">已通过</el-tag>
                    <el-tag type="danger" size="mini" v-else>未通过</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="认证资料">
                    <el-link :underline="false" type="primary" :href="business.papersImg">点击下载材料</el-link>
                </el-descriptions-item>
            </el-descriptions>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" size="mini" @click="handlePending(2)"  :disabled="disabledBtn">通&nbsp;过</el-button>
                <el-button type="danger" size="mini" @click="handlePending(3)" :disabled="disabledBtn">不通过</el-button>
            </span>
        </el-dialog>
    </div>
  </template>
<script>
import { saveBusiness,getBusiness,getById } from '@/api/business';

  export default {
    name:'BusinessInfo',
    components:{  },
    data() {
      return {
        tabName:'pending',
        businessesDone:[],
        businessesPending:[],
        condition:{
            current:1,
            size:20,
            keywords:'',
            status:[1],
        },
        total:0,
        business:{},
        disabledBtn: false,
        dialogVisible: false,
      }
    },
    mounted(){
        this.getBusiness()
    },
    methods: {
        // 分页查询
        async getBusiness(){
            // 查询未审核
            this.condition.status = [1]
            await getBusiness(this.condition).then(resp => {
                if(resp.code === 200){
                    this.businessesPending = resp.data.records
                }
            })
            // 查询已审核
            this.condition.status = [2,3]
            await getBusiness(this.condition).then(resp => {
                if(resp.code === 200){
                    this.businessesDone = resp.data.records
                }
            })
        },
        // 查看详情
        async details(businessId,disabledBtn){
            this.disabledBtn = disabledBtn
            let res = await getById(businessId,'');
            if(res.code === 200){
                this.business = res.data
                this.dialogVisible = true;
            }
        },
        async handlePending(status){
            this.business.status = status
            let res = await saveBusiness(this.business)
            if(res.code === 200){
                this.dialogVisible = false
                this.getBusiness()
            }
        }
    }
  }
  </script>
  
  <style scoped>
  .handle-box {
    margin-bottom: 20px;
    /* height: 10px; */
    display: flex;
    flex-direction: row;
}

.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
  .table-td-thumb {
    display: block;
    margin: auto;
    width: 88px;
    height: 88px;
}
  </style>
