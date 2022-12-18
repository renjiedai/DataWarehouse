<template>
<div id="viewport">
  <!-- Sidebar -->
  <div id="sidebar">
    <header>
      <a>功能列表</a>
    </header>
    <ul class="nav">
      <li>
        <a href="#/searchmovie">
          <i class="zmdi zmdi-view-dashboard"></i>电影信息查询 
        </a>
      </li>
      <li>
        <a href="#/cooperate">
          <i class="zmdi zmdi-link"></i> 合作关系查询
        </a>
      </li>
      <li>
        <a href="#/combineinfo">
          <i class="zmdi zmdi-widgets"></i> 组合信息查询
        </a>
      </li>
      <li>
        <a href="#/traceback">
          <i class="zmdi zmdi-calendar"></i> 电影溯源查询
        </a>
      </li>
    </ul>
  </div>
  <!-- Content -->
  <!-- <div id="content">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href="#"><i class="zmdi zmdi-notifications text-danger"></i>
            </a>
          </li>
          <li><a href="#">Test User</a></li>
        </ul>
      </div>
    </nav>
    <div class="container-fluid">
      <h1>Simple Sidebar</h1>
      <p>
        Make sure to keep all page content within the 
        <code>#content</code>.
      </p>
    </div>
  </div> -->
</div>
</template>

<script>
export default {
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500");

body {
  overflow-x: hidden;
  font-family: "Roboto", sans-serif;
  font-size: 16px;
}


/* Sidebar Styles */

#sidebar {
  z-index: 1000;
  position: fixed;
  left: 250px;
  width: 210px;
  height: 100%;
  margin-left: -250px;
  margin-top: -8px;
  overflow-y: auto;
  background: #2b3f4a;
  -webkit-transition: all 0.5s ease;
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

#sidebar header {
  background-color: #263238;
  font-size: 20px;
  line-height: 52px;
  text-align: center;
}

#sidebar header a {
  color: #fff;
  display: block;
  text-decoration: none;
}

#sidebar header a:hover {
  color: #fff;
}

#sidebar .nav li {
    margin-bottom: 35px;
}

#sidebar .nav a {
  background: none;
  border-bottom: 1px solid #455a64;
  color: #cfd8dc;
  font-size: 18px;
  /* margin: 15px; */
  /* padding: 16px 24px; */
}

#sidebar .nav a:hover {
  background: none;
  color: #eceff1;
}

#sidebar .nav a i {
}

</style>

