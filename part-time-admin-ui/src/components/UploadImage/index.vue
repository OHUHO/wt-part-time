<template>
    <div class="upload">
        <vue-upload-multiple-image
            class='upload-vue'
            @upload-success='uploadImageSuccess'
            @before-remove='beforeRemove'
            @edit-image='editImage'
            @data-change='dataChange'
            :max-image='maxImage'
            :drag-text="dragText"
            :browse-text='browseText'
            :mark-is-primary-text='markIsPrimaryText'
            :popup-text='popupText'
            :primary-text='primaryText'
            :data-images='images' />
    </div>
</template>
  
<script>

import { upload } from '@/api/file';
import VueUploadMultipleImage from 'vue-upload-multiple-image';
  
  export default {
    name: 'UploadImage',
    components:{ VueUploadMultipleImage },
    props :{
        dragText: {
            type:String,
            default:'点击添加图片'
        },
        browseText: {
            type:String,
            default:'图片上传'
        },
        markIsPrimaryText:{
            type: String,
            default:'图片'
        },
        popupText: {
            type:String,
            default:'添加系统的图片'
        },
        primaryText: {
            type:String,
            default:'兼职平台-图片'
        },
        maxImage: {
            type:Number,
            default:1
        },
        
    },
    data(){
        return {
            images:[],
            fileUrl:[],
        }
    },
    methods: {
        uploadImageSuccess(formData, index, fileList) {
            console.log('fileList=>', fileList);
            fileList.forEach(file => {
                let formdata = new FormData();
                formData.append("fileName",file.name)
                formdata.append('file', file.path);
                upload(formData).then(resp => {
                    if (resp.code === 200) {
                        console.log(resp.data)
                        this.fileUrl.push(resp.data)
                    }
                })
            });
            // console.log(this.fileUrl)
        },
        beforeRemove(index, done, fileList) {
            console.log('index', index, fileList);
            const r = confirm('remove image');
            if (r === true) {
                done();
            } else {
            }
        },
        editImage(formData, index, fileList) {
            console.log('edit data', formData, index, fileList);
        },
        dataChange(data) {
            console.log(data);
        },
    }
  }
  </script>
  
<style scoped>
::v-deep .upload-vue .image-container{
    background-color: unset;
    width: 100%;
}
</style>
  