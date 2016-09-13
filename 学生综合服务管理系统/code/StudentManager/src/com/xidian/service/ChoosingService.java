package com.xidian.service;

import java.util.List;

import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Student;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;

public interface ChoosingService {

	/**
	 * 保存选课结果
	 * 
	 * @param teach
	 */
	void saveChoosing(Teach teach);

	/**
	 * 查询学生的选课
	 * 
	 * @param student
	 * @return
	 */
	List<Choosing> findChoosings(Student student);

	/**
	 * 查询选课结果
	 * 
	 * @return
	 */
	List<Choosing> queryResult();

	/**
	 * 查询选修课学生成绩
	 * 
	 * @param course
	 * @param teacher
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Choosing> queryStudentElectives(Course course, Teacher teacher,
			int page, int offset);

	/**
	 * 退课
	 * 
	 * @param choosing
	 */
	void deleteChoosing(Choosing choosing);

}
