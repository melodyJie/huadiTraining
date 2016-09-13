package com.xidian.service;

import java.util.List;

import com.xidian.data.Data;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;

public interface StudentService {

	/**
	 * 查询学生
	 * 
	 * @param s
	 * @return
	 */
	List<Student> queryStudent(Student s);

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 */
	void update(Student student);

	/**
	 * 获取个人信息
	 * 
	 * @return
	 */
	List<Data> getPersonalMessage();

	/**
	 * 添加学生
	 * 
	 * @param student
	 */
	void addStudent(Student student);

	/**
	 * 查询所有学生
	 * 
	 * @param studentclasses
	 * @param grade
	 * @param page
	 * @param offset
	 * @return
	 */
	List<Student> queryAllStudent(List<Studentclass> studentclasses, int grade,
			int page, int offset);

	/**
	 * 修改学生信息
	 * 
	 * @param student
	 */
	void updateStudent(Student student);

	/**
	 * 删除学生信息
	 * 
	 * @param student
	 */
	void deleteStudent(Student student);

	/**
	 * 根据属性查找学生
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Student> findByProperty(String propertyName, Object value);

	/**
	 * 根据专业课程查询学生
	 * 
	 * @param specialtycourse
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Student> queryStudentCourse(Specialtycourse specialtycourse,
			int page, int offset);
}
