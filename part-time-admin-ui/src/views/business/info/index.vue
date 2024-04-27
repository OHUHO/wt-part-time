<template>
    <div class="app-container">
        <div class="container">
            <div class="handle-box">
                <el-input v-model="condition.keywords" placeholder="请输入关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="getBusiness">搜索</el-button>
            </div>
            <el-table border
                :data="businesses"
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                style="width: 100%"
                :default-sort = "{prop: 'successive', order: 'descending'}"
            >
                <!-- <el-table-column prop="businessId" label='Id' width="180" align='center'></el-table-column> -->
                <el-table-column prop="storeName" label="店铺名称" sortable align='center'></el-table-column>
                <el-table-column prop="storeType" label="店铺类型" sortable align='center'></el-table-column>
                <el-table-column prop="username" label="姓名" sortable align='center'></el-table-column>
                <el-table-column label="性别" width="60" align='center'>
                    <template slot-scope="scope">
                        <span v-if="scope.row.gender === '1'" >男</span>
                        <span v-else-if="scope.row.gender === '2'" >女</span>
                        <span v-else>未知</span>
                    </template>
                </el-table-column>
                <el-table-column prop="phone" label="手机号" align='center'></el-table-column>
                <el-table-column prop="cardNo" label="身份证号" align='center'></el-table-column>

                <el-table-column prop="papersType" label="证件类型" sortable align='center'></el-table-column>
                <el-table-column prop="papersNumber" label="证件号码" sortable align='center'></el-table-column>
                <!-- <el-table-column prop="papersImg" label="证明材料" sortable align='center'></el-table-column> -->

                <el-table-column prop="createTime" label="注册时间" width="180" sortable align='center'></el-table-column>
                <el-table-column prop="createUserName" label="注册人" width="100" sortable align='center'></el-table-column>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteBusiness(scope.$index, scope.row.businesssId)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total,sizes,prev, pager, next"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-sizes="[20,40,60,80,100]"
                    :current-page="condition.current"
                    :page-size="condition.size"
                    :total="total"
                ></el-pagination>
            </div>
        </div>
    </div>
  </template>
<script>
import { saveBusiness,getBusiness,deleteBusiness } from '@/api/business';

  export default {
    name:'BusinessInfo',
    components:{  },
    data() {
      return {
        businesses:[],
        condition:{
            current:1,
            size:20,
            keywords:'',
            status:[2]
        },
        total:0,
      }
    },
    mounted(){
        this.getBusiness()
    },
    methods: {
        // 删除
        deleteBusiness(index, businesssId) {
            this.$confirm('确认删除该商家信息?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteBusiness(businesssId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getBusiness()
                    }
                })
            }).catch(() => {
                // 取消
            });
        },
        // 分页查询
        getBusiness(){
            getBusiness(this.condition).then(resp => {
                if(resp.code === 200){
                    this.businesses = resp.data.records
                    this.total = resp.data.total
                }
            })
        },
        handleSizeChange(val){
            // console.log("current size",val)
            this.condition.size = val
            this.getBusiness()
        },
        handleCurrentChange(val) {
            // console.log("current index",val)
            this.condition.current = val
            this.getBusiness()
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
