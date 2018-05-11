<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="userarea/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="id" id="id" value="${pd.id}"/>
						<input type="hidden" name="level" id="level" value="${pd.level}" />
						<input type="hidden" name="parentid" id="parentid" value="${pd.parentid}" />
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">拼音首字母简写:</td>
								<td><input type="text" name="pingyin" id="pingyin" value="${pd.pingyin}" maxlength="32" placeholder="这里输入拼音首字母简写" title="拼音首字母简写" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">区域名称:</td>
								<td><input type="text" name="areaname" id="areaname" value="${pd.areaname}" maxlength="100" placeholder="这里输入区域名称" title="区域名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">区域简称:</td>
								<td><input type="text" name="shortname" id="shortname" value="${pd.shortname}" maxlength="64" placeholder="这里输入区域简称" title="区域简称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">经度:</td>
								<td><input type="number" name="lng" id="lng" value="${pd.lng}" maxlength="32" placeholder="这里输入经度" title="经度" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">纬度:</td>
								<td><input type="number" name="lat" id="lat" value="${pd.lat}" maxlength="32" placeholder="这里输入纬度" title="纬度" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">区域级别:</td>
								<td><input type="number" name="level" id="level" value="${pd.level}" maxlength="32" placeholder="这里输入区域级别" title="区域级别" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#pingyin").val()==""){
				$("#pingyin").tips({
					side:3,
		            msg:'请输入拼音首字母简写',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#pingyin").focus();
			return false;
			}
			if($("#areaname").val()==""){
				$("#areaname").tips({
					side:3,
		            msg:'请输入区域名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#areaname").focus();
			return false;
			}
			if($("#shortname").val()==""){
				$("#shortname").tips({
					side:3,
		            msg:'请输入区域简称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#shortname").focus();
			return false;
			}
			if($("#lng").val()==""){
				$("#lng").tips({
					side:3,
		            msg:'请输入经度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#lng").focus();
			return false;
			}
			if($("#lat").val()==""){
				$("#lat").tips({
					side:3,
		            msg:'请输入纬度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#lat").focus();
			return false;
			}
			if($("#level").val()==""){
				$("#level").tips({
					side:3,
		            msg:'请输入区域级别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#level").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>