<template>
    <div class="app-container">
        <div class="container">
            <div class="handle-box">
                <el-input v-model="condition.keywords" placeholder="请输入关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="getExperience">搜索</el-button>
            </div>
            <el-table border
                :data="experience"
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                style="width: 100%"
                :default-sort = "{prop: 'successive', order: 'descending'}"
            >
                <el-table-column label="经验标题" align='center' width="240">
                    <template slot-scope="scope">
                        <el-link type="primary" :underline="false" @click="details(scope.row)">{{ scope.row.name }}</el-link>
                    </template>
                </el-table-column>
                <el-table-column prop="content" label="经验内容" align='center'></el-table-column>
                <el-table-column prop="username" label="发布者" align='center' width="120"></el-table-column>
                <el-table-column prop="createTime" label="发布时间" width="180" sortable align='center'></el-table-column>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-delete" class="red" 
                        @click="deleteExperience(scope.row.experienceId)">删除</el-button>
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

        <el-dialog
            title="经验评论信息"
            :visible.sync="dialogVisible"
            :close-on-click-modal="false"
            width="66%"
            center>
            <el-table :data="comments">
                <el-table-column property="fromUserName" label="评论用户"/>
                <el-table-column property="content" label="评论内容" />
                <el-table-column property="commentTime" label="评论时间" width="160"/>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" 
                            icon="el-icon-delete" 
                            class="red" 
                            @click="deleteComment(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- DOTO：分页 -->
        </el-dialog>

    </div>
  </template>
<script>
import { getExperience,deleteExperience } from '@/api/experience';
import { getList,deleteComment } from '@/api/comment';

  export default {
    name:'Experience',
    components:{  },
    data() {
      return {
        experience:[],
        condition:{
            current:1,
            size:20,
            keywords:'',
        },
        total:0,
        // 报名信息弹窗
        dialogVisible: false,
        // 评论信息
        comments: [],
      }
    },
    mounted(){
        this.getExperience()
    },
    methods: {
        // 删除经验
        deleteExperience(experienceId) {
            this.$confirm('确认删除该经验?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteExperience(experienceId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getExperience()
                    }
                })
            }).catch(() => {
                // 取消
            });
        },
        // 分页查询
        getExperience(){
            getExperience(this.condition).then(resp => {
                if(resp.code === 200){
                    this.experience = resp.data.records
                    this.total = resp.data.total
                }
            })
        },
        details(experience){
            // 查询经验的评论信息
            this.getComments(experience.experienceId)
            this.dialogVisible = true
        },
        handleSizeChange(val){
            this.condition.size = val
            this.getExperience()
        },
        handleCurrentChange(val) {
            this.condition.current = val
            this.getExperience()
        },
        // c查询评论信息
        getComments(experienceId){
            getList({experienceId:experienceId}).then(resp => {
                if(resp.code === 200){
                    this.comments = resp.data
                }
            })
        },
        // 删除评论
        deleteComment(comment){
            this.$confirm('确认删除该评论?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteComment(comment.commentId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getComments(comment.experienceId)
                    }
                })
            }).catch(() => {
                // 取消
            });
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
