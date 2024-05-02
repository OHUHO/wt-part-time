<template>
  <div class="app-container">
    <div class="container">
      <div class="handle-box">
        <el-input v-model="condition.keywords" placeholder="请输入关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="getUsers">搜索</el-button>
      </div>
      <el-table border
        :data="users"
        class="table"
        ref="multipleTable"
        header-cell-class-name="table-header"
        style="width: 100%"
        :default-sort = "{prop: 'successive', order: 'descending'}"
      >
        <el-table-column prop="id" label="用户Id" align='center' width="188"></el-table-column>
        <el-table-column prop="username" label="用户昵称" align='center'></el-table-column>
        <el-table-column label="性别" width="60" align='center'>
          <template slot-scope="scope">
            <span v-if="scope.row.gender === '1'" >男</span>
            <span v-else-if="scope.row.gender === '2'" >女</span>
            <span v-else>未知</span>
          </template>
        </el-table-column>
        <el-table-column label="头像"align="center">
          <template slot-scope="scope">
            <el-image class="table-td-thumb" :src="scope.row.portrait" :preview-src-list="[scope.row.portrait]"/>
          </template>
        </el-table-column>

        <el-table-column prop="createTime" label="注册时间" width="200" sortable align='center'></el-table-column>
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
import { getUsers } from '@/api/user';

export default {
  name:'User',
  components:{  },
  data() {
    return {
      users:[],
      condition:{
          current:1,
          size:20,
          keywords:'',
      },
      total:0,
    }
  },
  mounted(){
      this.getUsers()
  },
  methods: {
    // 分页查询
    getUsers(){
      getUsers(this.condition).then(resp => {
        if(resp.code === 200){
          this.users = resp.data.records
          this.total = resp.data.total
        }
      })
    },
    handleSizeChange(val){
      this.condition.size = val
      this.getExperience()
    },
    handleCurrentChange(val) {
      this.condition.current = val
      this.getExperience()
    },
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
