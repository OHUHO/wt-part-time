<template>
    <div class="app-container">
        <div class="container">
            <div class="handle-box">
                <el-input v-model="condition.keywords" placeholder="请输入关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="getJob">搜索</el-button>
            </div>
            <el-table border
                :data="jobs"
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                style="width: 100%"
                :default-sort = "{prop: 'successive', order: 'descending'}"
            >
                <el-table-column label="兼职名称" align='center'>
                    <template slot-scope="scope">
                        <el-link type="primary" :underline="false" @click="details(scope.row)">{{ scope.row.name }}</el-link>
                    </template>
                </el-table-column>
                <el-table-column prop="content" label="兼职内容" align='center'></el-table-column>
                <el-table-column prop="typeName" label="兼职类型" align='center' width="100"></el-table-column>
                <el-table-column label="薪资" align='center' width="80">
                    <template slot-scope="scope">
                        <span v-if="scope.row.salaryType === '1'" >{{scope.row.salary}}/小时</span>
                        <span v-else-if="scope.row.salaryType === '2'" >{{scope.row.salary}}/天</span>
                        <span v-else-if="scope.row.salaryType === '3'" >{{scope.row.salary}}/周</span>
                        <span v-else-if="scope.row.salaryType === '4'" >{{scope.row.salary}}/月</span>
                        <span v-else>{{scope.row.salary}}/次</span>
                    </template>
                </el-table-column>
                <el-table-column label="结算方式" align='center' width="78">
                    <template slot-scope="scope">
                        <span v-if="scope.row.payType === '1'" >日结</span>
                        <span v-else-if="scope.row.payType === '2'" >周结</span>
                        <span v-else-if="scope.row.payType === '3'" >月结</span>
                        <span v-else>完工结</span>
                    </template>
                </el-table-column>
                <el-table-column prop="count" label="数量(人)" width="76" align='center'></el-table-column>
                <el-table-column label="性别" width="50" align='center'>
                    <template slot-scope="scope">
                        <span v-if="scope.row.gender === '1'" >男</span>
                        <span v-else-if="scope.row.gender === '2'" >女</span>
                        <span v-else>不限</span>
                    </template>
                </el-table-column>
                <el-table-column label="兼职时间" align='center' width="190">
                    <template slot-scope="scope">
                        <span>{{ scope.row.beginTime.substring(0, 10) }} ～ {{ scope.row.endTime.substring(0, 10) }}</span>
                    </template>
                </el-table-column>
                <!-- <el-table-column label="结束时间" align='center' width="96">
                    <template slot-scope="scope">
                        <span>{{ scope.row.endTime.substring(0, 10) }}</span>
                    </template>
                </el-table-column> -->
                <el-table-column prop="address" label="地址" align='center'></el-table-column>
                <el-table-column prop="storeName" label="所属店铺" width="100" align='center'></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" sortable align='center'></el-table-column>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteJob(scope.row.jobId)">删除</el-button>
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

        <!-- 兼职报名用户信息 -->
        <el-dialog
            :title="dialogTitle"
            :visible.sync="dialogVisible"
            :close-on-click-modal="false"
            width="50%"
            center>
            代办：兼职报名用户信息
            <el-table :data="registration">
                <el-table-column property="date" label="日期" width="150"></el-table-column>
                <el-table-column property="name" label="姓名" width="200"></el-table-column>
                <el-table-column property="address" label="地址"></el-table-column>
            </el-table>
            <!-- DOTO：分页 -->
        </el-dialog>
    </div>
  </template>
<script>
import { getJob,deleteJob } from '@/api/job';

  export default {
    name:'JobInfo',
    components:{  },
    data() {
      return {
        jobs:[],
        condition:{
            current:1,
            size:20,
            keywords:'',
            // status:[2]
        },
        total:0,
        dialogTitle:'',
        dialogVisible: false,
        registration: [
            {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'}, 
            {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }
        ],
      }
    },
    mounted(){
        this.getJob()
    },
    methods: {
        // 删除
        deleteJob(jobId) {
            this.$confirm('确认删除该兼职?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteJob(jobId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getJob()
                    }
                })
            }).catch(() => {
                // 取消
            });
        },
        // 分页查询
        getJob(){
            getJob(this.condition).then(resp => {
                if(resp.code === 200){
                    this.jobs = resp.data.records
                    this.total = resp.data.total
                }
            })
        },
        details(job){
            console.log(job)
            // 查询兼职报名信息
            // TODO
            // this.registration = res.data
            this.dialogTitle = job.name + " 报名详情"
            this.dialogVisible = true
        },
        handleSizeChange(val){
            this.condition.size = val
            this.getJob()
        },
        handleCurrentChange(val) {
            this.condition.current = val
            this.getJob()
        }
    }
  }
  </script>
  
  <style scoped>
  .handle-box {
    margin-bottom: 20px;
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
