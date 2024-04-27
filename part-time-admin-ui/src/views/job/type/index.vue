<template>
    <div class="app-container">

        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-document-add"
                    class="handle-del mr10"
                    @click="addVisible = true">
                    新增兼职种类
                </el-button>
            </div>
            <el-table border
                :data="jobTypes"
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                style="width: 100%"
                :default-sort = "{prop: 'successive', order: 'descending'}"
            >
                <el-table-column prop="typeId" label='ID' width="180" align='center'></el-table-column>
                <el-table-column prop="typeName" label="类型名称" sortable width="180" align='center'></el-table-column>
                <el-table-column prop="description" label="类型描述" sortable width="260" align='center'></el-table-column>
                <el-table-column label="封面图标"align="center">
                    <template slot-scope="scope">
                        <el-image class="table-td-thumb" :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="grade" label="顺序" sortable width="180" align='center'></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" sortable align='center'></el-table-column>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-warning-outline"  @click="editJobType(scope.$index, scope.row.typeId)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteJobType(scope.$index, scope.row.typeId)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    
        <!-- 弹出框 -->
        <el-dialog title='编辑兼职种类'
            :visible.sync='addVisible'
            :close-on-click-modal='false'
            width='50%'>
            <el-form ref='form' :model='jobType' label-width='70px'>
                <el-form-item label="类型名称">
                    <el-input v-model="jobType.typeName" placeholder="类型名称"></el-input>
                </el-form-item>
                <el-form-item label="类型描述">
                    <el-input v-model="jobType.description" type="textarea" placeholder="类型描述"></el-input>
                </el-form-item>
                <el-form-item label='封面图标'>
                    <upload-image ref="upload" :max-image="1"/>
                </el-form-item>
                <el-form-item label="顺序">
                    <el-input-number v-model="jobType.grade" :step="1" :min="1" :max="999"></el-input-number>
                </el-form-item>
            </el-form>
            <span slot='footer' class='dialog-footer'>
                <el-button @click='closeDialog'>取 消</el-button>
                <el-button type='primary' @click='addJobType'>确 定</el-button>
            </span>
        </el-dialog>
    </div>
  </template>  
<script>
import { saveJobType,getJobType,deleteJobType,getById } from '@/api/jobType';
import UploadImage from '@/components/UploadImage'

export default {
    name:'JobType',
    components:{ UploadImage },
    data() {
      return {
        addVisible: false,
        jobTypes:[],
        // 新增兼职种类
        jobType:{
            typeId:null,
            typeName:'',
            description:'',
            cover:'',
            grade:999,
            createUserId:'',
            createUserName:'',
        },
        condition:{
            current:1,
            size:99999,
            keywords:'',
        }
      }
    },
    mounted(){
        this.getJobType()
    },
    methods: {
        // 关闭Dialog
        closeDialog(){
            // 重置表单
            this.jobType = {
                typeId:null,
                typeName:'',
                description:'',
                cover:'',
                grade:999,
                createUserId:'',
                createUserName:'',
            }
            // 重置上传组件
            this.$refs.upload.images = []
            this.addVisible = false
        },
        // 编辑
        editJobType(index, typeId) {
            // console.log(index, typeId);
            this.addVisible = true
            getById(typeId).then(resp => {
                if(resp.code === 200){
                    this.jobType = resp.data
                }
            })
        },
        // 删除
        deleteJobType(index, typeId) {
            // console.log(index, typeId);
            this.$confirm('确认删除该轮播图?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteJobType(typeId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getJobType()
                    }
                })
            }).catch(() => {
                // 取消
            });
        },
        // 分页查询
        getJobType(){
            getJobType(this.condition).then(resp => {
                if(resp.code === 200){
                    this.jobTypes = resp.data.records
                }
            })
        },
        // 保存或更新
        addJobType(){
            // 从上传组件中获取图片地址
            let cover = this.$refs.upload.fileUrl[0];
            this.jobType.cover = cover;
            // 上传
            // console.log("add jobType - ", this.jobType)
            saveJobType(this.jobType).then(resp => {
                if(resp.code === 200){
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    })
                    this.closeDialog()
                    this.getJobType()
                }
            })
        }
    }
  }
  </script>
  
  <style scoped>
  .handle-box {
    margin-bottom: 20px;
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
  
  