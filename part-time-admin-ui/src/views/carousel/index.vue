<template>
    <div class="app-container">

        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-document-add"
                    class="handle-del mr10"
                    @click="addVisible = true">
                    添加轮播图
                </el-button>
            </div>
            <el-table border
                :data="carousels"
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                style="width: 100%"
                :default-sort = "{prop: 'successive', order: 'descending'}"
            >
                <el-table-column prop="carouselId" label='ID' width="180" align='center'></el-table-column>
                <el-table-column prop="name" label="名称" sortable width="180" align='center'></el-table-column>
                <el-table-column prop="description" label="描述" sortable width="260" align='center'></el-table-column>
                <el-table-column label="图片"align="center">
                    <template slot-scope="scope">
                        <el-image class="table-td-thumb" :src="scope.row.carouselUrl" :preview-src-list="[scope.row.carouselUrl]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" sortable align='center'></el-table-column>
                <el-table-column label="操作" width="160" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-warning-outline"  @click="editCarousel(scope.$index, scope.row.carouselId)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteCarousel(scope.$index, scope.row.carouselId)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    
        <!-- 弹出框 -->
        <el-dialog title='编辑轮播图'
            :visible.sync='addVisible'
            :close-on-click-modal='false'
            width='30%'>
            <el-form ref='form' :model='carousel' label-width='70px'>
                <el-form-item label="名称">
                    <el-input v-model="carousel.name" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="carousel.description" type="textarea" placeholder="描述"></el-input>
                </el-form-item>
                <el-form-item label='轮播图'>
                    <upload-image ref="upload" :max-image="1"/>
                </el-form-item>
            </el-form>
            <span slot='footer' class='dialog-footer'>
                <el-button @click='closeDialog'>取 消</el-button>
                <el-button type='primary' @click='addCarousel'>确 定</el-button>
            </span>
        </el-dialog>
    </div>
  </template>  
<script>
import { saveCarousel,getCarousel,deleteCarousel,getById } from '@/api/carousel';
import UploadImage from '@/components/UploadImage'

  export default {
    name:'Carousel',
    components:{ UploadImage },
    data() {
      return {
        addVisible: false,
        carousels:[],
        // 新增轮播图
        carousel:{
            carouselId:null,
            name:'',
            description:'',
            carouselUrl:'',
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
        this.getCarousel()
    },
    methods: {
        // 关闭Dialog
        closeDialog(){
            // 重置表单
            this.carousel = {
                carouselId:null,
                name:'',
                description:'',
                carouselUrl:'',
                createUserId:'',
                createUserName:'',
            }
            // 重置上传组件
            this.$refs.upload.images = []
            this.addVisible = false
        },
        // 编辑
        editCarousel(index, carouselId) {
            // console.log(index, carouselId);
            this.addVisible = true
            getById(carouselId).then(resp => {
                if(resp.code === 200){
                    this.carousel = resp.data
                }
            })
        },
        // 删除
        deleteCarousel(index, carouselId) {
            // console.log(index, carouselId);
            this.$confirm('确认删除该轮播图?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // 确定
                deleteCarousel(carouselId).then(resp => {
                    if(resp.code === 200){
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        })
                        this.getCarousel()
                    }
                })
            }).catch(() => {
                // 取消
            });
        },
        // 分页查询
        getCarousel(){
            getCarousel(this.condition).then(resp => {
                if(resp.code === 200){
                    this.carousels = resp.data.records
                }
            })
        },
        // 保存或更新
        addCarousel(){
            // 从上传组件中获取图片地址
            let carouselUrl = this.$refs.upload.fileUrl[0];
            this.carousel.carouselUrl = carouselUrl;
            // 上传
            console.log("add carousel - ", this.carousel)
            saveCarousel(this.carousel).then(resp => {
                if(resp.code === 200){
                    this.$message({
                        message: '保存成功！',
                        type: 'success'
                    })
                    this.closeDialog()
                    this.getCarousel()
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
  
  