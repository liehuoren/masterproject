<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">
	
	<!--表名 -->
	<sql id="tableName">
		${tabletop}${objectNameUpper}
	</sql>
	
	<!-- 字段 -->
	<sql id="Field">
	<#list fieldList as var>
		${var[0]},	
	</#list>
		${objectNameUpper}_ID
	</sql>
	
	<!-- 字段值 -->
	<sql id="FieldValue">
	<#list fieldList as var>
		${r"#{"}${var[0]}${r"}"},	
	</#list>
		${r"#{"}${objectNameUpper}_ID${r"}"}
	</sql>
	
	<!-- 新增-->
	<insert id="save" parameterType="pd">
		insert into 
	<include refid="tableName"></include>
		(
	<include refid="Field"></include>
		) values (
	<include refid="FieldValue"></include>
		)
	</insert>
	
	<!-- 删除-->
	<delete id="delete" parameterType="pd">
		delete from
		<include refid="tableName"></include>
		where 
			${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</delete>
	
	<!-- 修改 -->
	<update id="edit" parameterType="pd">
		update
		<include refid="tableName"></include>
		set 
	<#list fieldList as var>
		<if test="${var[0]}!= null and ${var[0]} != ''">
			${var[0]}=${r"#{"}${var[0]}${r"}"},
		</if>
	</#list>
	
		${objectNameUpper}_ID = ${objectNameUpper}_ID
		where 
		${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</update>
	
	<!-- 通过ID获取数据 -->
	<select id="findById" parameterType="pd" resultType="pd">
		SELECT 
		<include refid="Field"></include>
		FROM 
		<include refid="tableName"></include>
		WHERE 
		<if test="${objectNameUpper}_ID!= null and ${objectNameUpper}_ID != ''">
			${objectNameUpper}_ID=${r"#{"}${objectNameUpper}_ID${r"}"}
		</if>
		<if test="${objectNameUpper}_ID== null or ${objectNameUpper}_ID == ''">
			${objectNameUpper}_ID=NULL
		</if>
	</select>
	<!-- 通过ID获取并锁行  -->
	<select id="findByIdForLock" parameterType="pd" resultType="pd">
		SELECT 
		<include refid="Field"></include>
		FROM
		<include refid="tableName"></include>
		WHERE
		<if test="${objectNameUpper}_ID!= null and ${objectNameUpper}_ID != ''">
			${objectNameUpper}_ID=${r"#{"}${objectNameUpper}_ID${r"}"} FOR UPDATE
		</if>
		<if test="${objectNameUpper}_ID== null or ${objectNameUpper}_ID == ''">
			${objectNameUpper}_ID=NULL
		</if>
	</select>
	
	<!-- 列表 -->
	<select id="datalistPage" parameterType="page" resultType="pd">
		select
		<include refid="Field"></include>
		from 
		<include refid="tableName"></include>
		where  1=1 
		
		<#list fieldList as var>
		<if test="pd.${var[0]}!= null and pd.${var[0]} != ''">
			AND ${var[0]}=${r"#{pd."}${var[0]}${r"}"}
		</if>
		</#list>
	</select>
	
	<!-- 列表(全部) -->
	<select id="listAll" parameterType="pd" resultType="pd">
		select
		<include refid="Field"></include>
		from 
		<include refid="tableName"></include>
	</select>
	
	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		delete from
		<include refid="tableName"></include>
		where 
			${objectNameUpper}_ID in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
                 ${r"#{item}"}
		</foreach>
	</delete>
	
</mapper>