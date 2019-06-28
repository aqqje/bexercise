## SQL 训练

------

1、查找入职职员工时间排名倒第三的员工所有信息

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
-- 单个情况(不后推荐)
SELECT * 
FROM employees 
ORDER BY hire_date DESC LIMIT 2,1
-- 多个情况
SELECT * 
FROM employess 
WHERE hire_date = (
    SELECT DISTINCT hire_date 
    FROM employees 
    ORDER BY hire_date DESC LIMIT 2,1
)
```

------

2、查找最晚入职员工的所有信息

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
SELECT * 
FROM employees 
where hire_date = ( 
    SELECT MAX(hire_date) 
    FROM employees 
)
```

------

3、查找各个部门当前 (to_date='9999-01-01') 领导当前薪水详情以及其对应部门编号 dept_no

```sql
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('dept_no', 'emp_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT s.*, d.dept_no
FROM salaries AS s, dept_manager AS d 
WHERE s.emp_no = d.emp_no 
AND s.to_date = ('9999-01-01') 
AND d.to_date = ('9999-01-01')
```

------

4、查找所有已经分配部门的员工的 last_name 和 first_name

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
-- 使用 INNER JOIN
SELECT e.last_name, e.first_name, d.detp_no
FROM employees AS e INNER JOIN dept_emp AS d 
ON e.emp_no=d.emp_no
-- 使用 NATURAL JOIN
SELECT e.last_name, e.first_name, d.detp_no
FROM employees AS e NATURAL JOIN dept_emp AS d 
```

------

5、查找所有员工的 last_name 和 first_name 以及对应部门编号 dept_no, 也包括展示没有分配具体部门的员工

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
SELECT e.last_name, e.first_name, de.dept_no
FROM employees e LEFT JOIN dept_emp de ON 
e.emp_no = de.emp_no
```

------

6、查找所有员工入职时候的薪水情况，给出 emp_no 以及 salary, 并按照 emp_no 进行逆序

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);

```

答案：

```sql
-- 方法一(并列查询)
SELECT s.emp_no, s.salary
FROM employees AS e, salaries AS s 
WHERE e.emp_no = s.emp_no 
AND e.hire_date = s.from_date
ORDER BY e.emp_no DESC
-- 方法二(inner join 查询)
SELECT s.emp_no, s.salary
FROM employees AS e INNER JOIN salaries AS s 
ON e.emp_no = s.emp_no 
AND e.hire_date = s.from_date
ORDER BY e.emp_no DESC
```

------

7、查找薪水涨幅超过 15 次 的员工 号 emp_no 以及对应的涨幅次数 t

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT emp_no, COUNT(salary) AS t
FROM salaries
GROUP BY emp_no HAVING t > 15 
```

------

8、找出所有员工当前(to_date="9999-01-01")具体的薪水 salary 情况， 对于相同的薪水只显示一次， 并按照逆序显示

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 使用 distict 去重(不推荐)
SELECT DISTINCT salary 
FROM salaries 
WHERE to_date="9999-01-01"
ORDER BY salary DESC
-- 使用 group by 去重 （推荐）
SELECT salary 
FROM salaries 
WHERE to_date="9999-01-01"
GROUP BY salary
ORDER BY salary DESC
```

------

9、获取所有部门当前  manager 的当前薪水情况，给出 dept_no, emp_no 以及 salary， 当前表示 to_date=('9999-01-01')

```sql
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 方法一
SELECT d.dept_no, d.emp_no, s.salary
FROM dept_manager AS d, salaries AS s
WHERE d.emp_no = s.emp_no 
AND d.to_date = "9999-01-01"
AND s.to_date = "9999-01-01"
-- 方法二
SELECT d.dept_no, d.emp_no, s.salary
FROM dept_manager d INNER JOIN salaries AS s
ON d.emp_no = s.emp_no 
AND d.to_date = "9999-01-01"
AND s.to_date = "9999-01-01"
```

------

10、获取所有非 manager 的员工 emp.no

```sql
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
-- 方法一
SELECT emp_no
FROM  employees
WHERE emp_no NOT IN (
    SELECT emp_no 
    FROM dept_manager
) 
-- 方法二
SELECT emp_no
FROM (SELECT * FROM employees LEFT JOIN dept_manager ON employees.emp_no = dept_manager.emp_no)
WHERE dept_no IS NULL
-- 方法三
SELECT e.emp_no 
FROM employees AS e LEFT JOIN dept_manager AS d ON e.emp_no = d.emp_no
WHERE dept_no IS NULL
```

------

11、获取所有员工 当前的 manager, 如果当前的 manager 是自己的话结果不显示，当前表示 to_date='9999-01-01',结果第一列给出当前员工的 emp_no，第二列给出其 manager 对应的 manager_no.

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
```

答案：

```sql
SELECT e.emp_no, m.emp_no AS manager_no
FROM dept_emp AS INNER JOIN e, dept_manager AS m ON e.dept_no = m.depte_no 
WHERE e.emp_no <> m.emp_no 
AND m.to_date = '9999-01-01'
AND e.to_date = '9999-01-01'

SELECT de.emp_no, dm.emp_no manager_no
FROM dept_emp de INNER JOIN dept_manager dm ON de.dept_no = dm.dept_no 
AND de.emp_no != dm.emp_no
AND de.to_date='9999-01-01'
AND dm.to_date='9999-01-01'
```

------

12、获取所有部门当前员工薪水最高的相关信息，给出dept_no, emp_no 以及对应的 salary。

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT de.dept_no, de.emp_no, MAX(s.salary)
FROM dept_emp de INNER JOIN salaries s ON 
de.emp_no = s.emp_no
AND de.to_date = '9999-01-01'
AND s.to_date = '9999-01-01'
GROUP BY de.dept_no
```



13、从 titles 表获取按照 title 进行分组，每组个数大于等下 2，给出 title 及对应的数目 t。

```sql
CREATE TABLE IF NOT EXISTS "titles" (
	'emp_no' int(11) NOT NULL,
    'title' varchar(50) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
);
```

答案：

```sql
SELECT title, COUNT(emp_no) AS t
FROM titles GROUP BY title
HAVING t >= 2
```

------

14、从 titles 表中获取 title 进行分组， 每组个数大于等于 2 ，给出 title 以及对应的数目 t, 注意对于重复的 emp_no 进行忽略。 

```sql
CREATE TABLE IF NOT EXISTS "titles" (
	'emp_no' int(11) NOT NULL,
    'title' varchar(50) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
);
```

答案：

```sql
SELECT title, COUNT(DISTINCT emp_no) AS t
FROM titles GROUP BY title
HAVING t >= 2
```

------

15、统计出当前各个 title 类型对应的员工当前薪水对应的平均工资，结果给出 title 以及平均工资 avg。

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
CREATE TABLE IF NOT EXISTS "titles" (
	'emp_no' int(11) NOT NULL,
    'title' varchar(50) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
);
```

答案：

```sql
SELECT t.title, AVG(s.salary)
FROM salaries AS s INNER JOIN titles AS t 
ON s.emp_no = t.emp_no 
AND s.to_date='9999-01-01'
AND t.to_date='9999-01-01'
GROUP BY t.title
```

------



16、查找 employees 表所有 emp_no 为奇数， 且 last_name 不为 Mary 的员工信息，并按照 hire_date 逆序排序

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
SELECT *
FROM employees
WHERE last_name IS NOT 'Mary' AND emp_no%2 = 1
ORDER BY hire_date DESC
```

------

17、获取当前 (to_date='9999-01-01') 薪水第二多的员工 的 emp.no 及其对应的薪水 salary

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 这里使用 GROUP BY 进行去重
SELECT emp_no, salary
FROM salaries
WHERE salary IN (
	SELECT salary 
    FROM salaries
    WHERE to_date='9999-01-01'
    GROUP BY salary
    ORDER BY salary DESC LIMIT 1,1
)
```

------



18、查找当前薪水 (to_date='9999-01-01') 排名第二多的员工编号 emp.no 薪水 salary、last_name 及 first_name, 不准使用 order by。

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT e.emp_no, MAX(s.salary), e.last_name, e.first_name
FROM employees e INNER JOIN salaries s ON e.emp_no = s.emp_no
WHERE s.to_date='9999-01-01' 
AND s.salary NOT IN (
    SELECT MAX(salary) 
    FROM salaries
    WHERE to_date='9999-01-01' 
)
```

------

19、查找所有员工的 last_name 和 first_name 以及对应的 dept_name, 也包括暂时没有分配部门的员工

```sql
CREATE TABLE 'departments' (
    'dept_no' char(4) NOT NULL,
    'dept_name' varchar(40) NOT NULL,
    PRIMARY KEY('dept_no')
);
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
```

答案：

```sql
SELECT e.last_name, e.first_name, d.dept_name
FROM (employees e LEFT JOIN dept_emp de ON e.emp_no=de.emp_no)
LEFT JOIN departments d ON d.dept_no = de.dept_no
```

------

20、查找员工编号 emp.no 为 10001 其自入职以来的薪水 salary 涨幅值 growth

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 方法一（当前最后的工资低于入职时工资时，此方法不通过）
SELECT MAX(salary) - MIN(salary) AS growth
FROM salaries WHERE emp_no = 10001
-- 方法二（推荐）
SELECT 
(SELECT salary FROM salaries WHERE emp_no = 10001 ORDER BY to_date DESC LIMIT 1)  - 
(SELECT salary FROM salaries WHERE emp_no = 10001 ORDER BY to_date LIMIT 1) 
AS growth
```

------

21、查找所有员工自入职以来的薪水涨幅情况，给出员工编号 emp_no 及其对应的薪水涨幅 growth， 并按照 growth 进行升序

```sql
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 方法一
SELECT e.emp_no, (s2.salary-s1.salary) growth
FROM (employees e INNER JOIN salaries s1 ON e.emp_no = s1.emp_no 
AND e.hire_date = s1.from_date) INNER JOIN salaries s2 ON e.emp_no = s2.emp_no 
AND s2.to_date='9999-01-01'
ORDER BY growth
-- 方法二
SELECT sCurrent.emp_no, (sCurrent.salary - sStart.salary) growth
FROM (SELECT s.emp_no, s.salary
     FROM employees e INNER JOIN salaries s ON
     e.emp_no = s.emp_no AND s.to_date = '9999-01-01') AS sCurrent
INNER JOIN (SELECT s.emp_no, s.salary
     FROM employees e INNER JOIN salaries s ON
     e.emp_no = s.emp_no AND s.from_date = e.hire_date) AS sStart
ON sCurrent.emp_no = sStart.emp_no
ORDER BY growth

```

------

22、统计各个部门对应员工涨幅的次数总和，给出部门编码 dept_no、部门名称dept_name以次数 sum

```sql
CREATE TABLE 'departments' (
    'dept_no' char(4) NOT NULL,
    'dept_name' varchar(40) NOT NULL,
    PRIMARY KEY('dept_no')
);
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 方法一
SELECT de.dept_no, d.dept_name, COUNT(s.salary) sum
FROM (dept_emp de INNER JOIN salaries s ON de.emp_no = s.emp_no) INNER JOIN departments d
ON de.dept_no = d.dept_no
GROUP BY de.dept_no
-- 方法二
SELECT d.dept_no, d.dept_name, COUNT(salary) sum
FROM dept_emp de, departments d, salaries s 
WHERE d.dept_no = de.dept_no
AND s.emp_no = de.emp_no
GROUP BY d.dept_no
```

------

23、对所有员工的当前(to_date='9999-01-01')薪水按照salary进行 1-N 的排名， 相同 salary 并列且按照 emp_no

```sql
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT s1.emp_no, s1.salary, COUNT(DISTINCT s2.salary) rank
FROM salaries s1 INNER JOIN salaries s2 ON 
s1.to_date='9999-01-01' AND s2.to_date='9999-01-01'
AND s1.salary <= s2.salary
GROUP BY s1.emp_no 
ORDER BY s1.salary DESC, s1.emp_no 
```

------

24、获取所有非 manager 员工当前的薪水情况，给出 dept_no、emp_no 以及 salary、当前表示 to_date='9999-01-01'

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'employees' (
	'emp_no' int(11) NOT NULL,
    'birth_date' date NOT NULL,
    'first_name' varchar(14) NOT NULL,
    'last_name' varchar(16) NOT NULL,
    'gender' char(1) NOT NULL,
    'hire_date' date NOT NULL,
    PRIMARY KEY('emp_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
-- 方法一
SELECT de.dept_no, s.emp_no, s.salary
FROM (employees AS e INNER JOIN salaries AS s 
      ON e.emp_no = s.emp_no 
      AND s.to_date = '9999-01-01') 
INNER JOIN dept_emp AS de 
ON de.emp_no = e.emp_no
WHERE de.emp_no NOT IN (
    SELECT emp_no 
    FROM dept_manager 
    WHERE to_date = '9999-01-01'
)
-- 方法二
SELECT de.dept_no, de.emp_no, s.salary
FROM dept_emp AS de INNER JOIN salaries AS s 
ON de.emp_no = s.emp_no 
AND s.to_date='9999-01-01'
WHERE de.emp_no NOT IN (
    SELECT emp_no 
    FROM dept_manager
)
```

------

25、获取员工其当前的薪水比其 manager 当前薪水还高的相关信息，当前表示 to_date=‘9999-01-01’，结果第一列给出员工的 emp_no， 第二列给出其 manager 的manager_no，第三列给出该员工当前的薪水 emp_salary,第四列给出该员工对应的 manager 当前的薪水 manager_salary

```sql
CREATE TABLE 'dept_emp' (
	'emp_no' int(11) NOT NULL,
    'dept_no' char(4) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'dept_manager' (
    'dept_no' char(4) NOT NULL,
	'emp_no' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'dept_no')
);
CREATE TABLE 'salaries' (
	'emp_no' int(11) NOT NULL,
    'salary' int(11) NOT NULL,
    'from_date' date NOT NULL,
    'to_date' date NOT NULL,
    PRIMARY KEY('emp_no', 'from_date')
);
```

答案：

```sql
SELECT dse.emp_no, dsm.emp_no AS manager_no, dse.salary AS emp_salary, dsm.salary AS manager_salary
FROM 
(SELECT *
 FROM dept_emp AS de INNER JOIN salaries AS s 
 ON de.emp_no = s.emp_no 
 AND s.to_date = '9999-01-01') AS dse
INNER JOIN
(SELECT * 
 FROM dept_manager AS de INNER JOIN salaries AS s 
 ON de.emp_no = s.emp_no 
 AND s.to_date = '9999-01-01') AS dsm
ON dse.dept_no = dsm.dept_no
WHERE dse.salary > dsm.salary
```

------

26、汇总各个部门当前员工的title类型的分配数目，结果给出部门编号dept_no、dept_name、其当前员工所有的title以及该类型title对应的数目count

```sql
CREATE TABLE `departments` (
`dept_no` char(4) NOT NULL,
`dept_name` varchar(40) NOT NULL,
PRIMARY KEY (`dept_no`)
);
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`)
);
CREATE TABLE IF NOT EXISTS `titles` (
`emp_no` int(11) NOT NULL,
`title` varchar(50) NOT NULL,
`from_date` date NOT NULL,
`to_date` date DEFAULT NULL
);
```

答案：

```sql
SELECT dm.dept_no, d.dept_name,t.title, count(t.title) as count
FROM (dept_emp dm INNER JOIN departments d ON d.dept_no = dm.dept_no) 
INNER JOIN titles t ON t.emp_no = dm.emp_no
AND dm.to_date = '9999-01-01' 
AND t.to_date = '9999-01-01'
GROUP BY dm.dept_no, t.title
```

------

27、给出每个员工每年薪水涨幅超过5000的员工编号emp_no、薪水变更开始日期from_date以及薪水涨幅值salary_growth，并按照salary_growth逆序排列。

提示：在sqlite中获取datetime时间对应的年份函数为strftime('%Y', to_date)

```sql
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`)
);
```

答案：

```sql
SELECT s2.emp_no, s2.from_date, (s2.salary - s1.salary) salary_growth 
FROM salaries s1, salaries s2
WHERE s1.emp_no = s2.emp_no 
AND salary_growth > 5000
AND ( strftime('%Y', s2.to_date) -  strftime('%Y', s1.to_date) ) = 1
AND ( strftime('%Y', s2.to_date) -  strftime('%Y', s1.to_date) ) = 1
ORDER BY salary_growth DESC
```

------

28、查找描述信息中包括robot的电影对应的分类名称以及电影数目，而且还需要该分类对应电影数量>=5部

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',  -- 电影id
title varchar(255) NOT NULL,  -- 电影名称
description text, -- 电影描述信息
PRIMARY KEY (film_id)
);
CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL , -- 电影分类id
name  varchar(25) NOT NULL,   -- 电影分类名称
`last_update` timestamp,  -- 电影分类最后更新时间
PRIMARY KEY ( category_id ) 
);
CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL, -- 电影id
category_id  tinyint(3)  NOT NULL, -- 电影分类id
`last_update` timestamp  -- 电影id和分类id对应关系的最后更新时间
);
```

答案：

```sql
SELECT c.name, COUNT(fc.film_id) 
FROM (SELECT category_id, COUNT(film_id) AS file_sum
FROM film_category GROUP BY category_id
HAVING COUNT(film_id) >= 5) cc, film f, category c, film_category fc
WHERE f.description  LIKE '%robot%' 
AND c.category_id = fc.category_id
AND fc.film_id = f.film_id
AND cc.category_id = c.category_id
```

------

29、使用join查询方式找出没有分类的电影id以及名称

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',  -- 电影id
title varchar(255) NOT NULL,  -- 电影名称
description text, -- 电影描述信息
PRIMARY KEY (film_id)
);
CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL , -- 电影分类id
name  varchar(25) NOT NULL,   -- 电影分类名称
`last_update` timestamp,  -- 电影分类最后更新时间
PRIMARY KEY ( category_id ) 
);
CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL, -- 电影id
category_id  tinyint(3)  NOT NULL, -- 电影分类id
`last_update` timestamp  -- 电影id和分类id对应关系的最后更新时间
);
```

答案：

```sql
SELECT f.film_id, f.title
FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id
WHERE fc.category_id IS NULL

```

------

30、使用子查询的方式找出属于Action分类的所有电影对应的title,description

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',  -- 电影id
title varchar(255) NOT NULL,  -- 电影名称
description text, -- 电影描述信息
PRIMARY KEY (film_id)
);
CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL , -- 电影分类id
name  varchar(25) NOT NULL,   -- 电影分类名称
`last_update` timestamp,  -- 电影分类最后更新时间
PRIMARY KEY ( category_id ) 
);
CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL, -- 电影id
category_id  tinyint(3)  NOT NULL, -- 电影分类id
`last_update` timestamp  -- 电影id和分类id对应关系的最后更新时间
);
```

答案：

```sql
SELECT f.title, f.description
FROM film f INNER JOIN film_category fc ON f.film_id = fc.film_id
WHERE fc.category_id IN (SELECT category_id FROM category WHERE name = 'Action')
```

------

31、获取select * from employees对应的执行计划

```sql
CREATE TABLE `employees` ( `emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

答案：

```sql
SELECT last_name || " " || first_name name
FROM employees
```

------

32、获取select * from employees对应的执行计划

答案：

```sql
explain select * from employees
```

------

33、创建一个actor表，包含如下列信息

| 列表        | 类型        | 是否为NULL | 含义                               |
| ----------- | ----------- | ---------- | ---------------------------------- |
| actor_id    | smallint(5) | not null   | 主键id                             |
| first_name  | varchar(45) | not null   | 名字                               |
| last_name   | varchar(45) | not null   | 姓氏                               |
| last_update | timestamp   | not null   | 最后更新时间，默认是系统的当前时间 |

答案：

```sql
CREATE TABLE actor(
    'actor_id' smallint(5) NOT NULL,
    'first_name' varchar(45) NOT NULL,
    'last_name' varchar(45) NOT NULL,
    'last_update' timestamp NOT NULL DEFAULT (datetime('now','localtime')),
    PRIMARY KEY ('actor_id')
);
```

------

34、对于表actor批量插入如下数据

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```

| actor_id | first_name | last_name | last_update         |
| -------- | ---------- | --------- | ------------------- |
| 1        | PENELOPE   | GUINESS   | 2006-02-15 12:34:33 |
| 2        | NICK       | WAHLBERG  | 2006-02-15 12:34:33 |

答案：

```sql
-- 方法一
INSERT INTO actor (actor_id, first_name, last_name, last_update) 
VALUES (1, 'PENELOPE', 'GUINESS', '2006-02-15 12:34:33')
,(2, 'NICK', 'WAHLBERG', '2006-02-15 12:34:33')
-- 方法二
INSERT INTO actor 
SELECT 
1, 'PENELOPE', 'GUINESS', '2006-02-15 12:34:33'
UNION SELECT
2, 'NICK', 'WAHLBERG', '2006-02-15 12:34:33'
```

------

35、对于表actor批量插入如下数据,如果数据已经存在，请忽略，不使用replace操作

```SQL
CREATE TABLE IF NOT EXISTS actor (

actor_id smallint(5) NOT NULL PRIMARY KEY,

first_name varchar(45) NOT NULL,

last_name varchar(45) NOT NULL,

last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))

```

| actor_id | first_name | last_name | last_update           |
| -------- | ---------- | --------- | --------------------- |
| '3'      | 'ED'       | 'CHASE'   | '2006-02-15 12:34:33' |

答案：

```sql
INSERT OR IGNORE INTO actor 
SELECT '3','ED','CHASE','2006-02-15 12:34:33'
```

------

36、对于如下表actor，其对应的数据为:

| actor_id | first_name | last_name | last_update         |
| -------- | ---------- | --------- | ------------------- |
| 1        | PENELOPE   | GUINESS   | 2006-02-15 12:34:33 |
| 2        | NICK       | WAHLBERG  | 2006-02-15 12:34:33 |

创建一个actor_name表，将actor表中的所有first_name以及last_name导入改表。 actor_name表结构如下：

| 列表       | 类型        | 是否为NULL | 含义 |
| ---------- | ----------- | ---------- | ---- |
| first_name | varchar(45) | not null   | 名字 |
| last_name  | varchar(45) | not null   | 姓氏 |

答案：

```sql
CREATE TABLE actor_name(
    'first_name' varchar(45) NOT NULL,
    'last_name' varchar(45) NOT NULL
);
INSERT INTO actor_name 
SELECT first_name, last_name FROM actor
```

------

37、针对如下表actor结构创建索引：

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime'))
);
```

对first_name创建唯一索引uniq_idx_firstname，对last_name创建普通索引idx_lastname

答案：

```sql
CREATE UNIQUE INDEX uniq_idx_firstname ON actor(first_name);
CREATE INDEX idx_lastname ON actor(last_name);
```

------

38、针对actor表创建视图actor_name_view，只包含first_name以及last_name两列，并对这两列重新命名，first_name为first_name_v，last_name修改为last_name_v：

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```

答案：

```sql
CREATE VIEW actor_name_view AS
SELECT first_name first_name_v, last_name last_name_v FROM actor
```



39、针对salaries表emp_no字段创建索引idx_emp_no，查询emp_no为10005, 使用强制索引。

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
create index idx_emp_no on salaries(emp_no);
```

答案：

```sql
SELECT * FROM salaries INDEXED BY idx_emp_no WHERE emp_no = 10005
```

------

40、在last_update后面新增加一列名字为create_date, 类型为datetime, NOT NULL，默认值为'0000 00:00:00'

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')));
```

答案：

```sql
ALTER TABLE actor ADD create_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00'
```

------

41、构造一个触发器audit_log，在向employees_test表中插入一条数据的时候，触发插入相关的数据到audit中。

```sql
CREATE TABLE employees_test(
ID INT PRIMARY KEY NOT NULL,
NAME TEXT NOT NULL,
AGE INT NOT NULL,
ADDRESS CHAR(50),
SALARY REAL
);
CREATE TABLE audit(
EMP_no INT NOT NULL,
NAME TEXT NOT NULL
);
```

答案：

```sql
CREATE TRIGGER audit_log AFTER INSERT ON employees_test
BEGIN 
    INSERT INTO audit VALUES (NEW.ID, NEW.NAME );
END;
```

------

42、删除emp_no重复的记录，只保留最小的id对应的记录。

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);

insert into titles_test values ('1', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('2', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('3', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('4', '10004', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('5', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('6', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('7', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01');
```

答案：

```sql
DELETE FROM titles_test
WHERE id NOT IN (
    SELECT MIN(id) 
    FROM titles_test
    GROUP BY emp_no
)
```

------

43、将所有to_date为9999-01-01的全部更新为NULL,且 from_date更新为2001-01-01。

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL
);

insert into titles_test values ('1', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('2', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('3', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('4', '10004', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('5', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('6', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('7', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01');
```

答案：

```sql
UPDATE titles_test SET from_date = '2001-01-01', to_date = NULL WHERE to_date = '9999-01-01'
```

------

44、

```sql

```

答案：

```sql
-- replace INTO 详情解说 https://blog.csdn.net/zhangjg_blog/article/details/23267761
REPLACE INTO titles_test VALUES (5, '10005', 'Senior Engineer', '1986-06-26', '9999-01-01')

/**
使用 REPLACE 对字符串的处理
x:要处理的字符串
y:被替换的字符串
z:替换后的字符串
**/
UPDATE titles_test SET emp_no=REPLACE(emp_no, 10001, 10005) WHERE id = 5
```

------

45、  将titles_test表名修改为titles_2017。

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);

insert into titles_test values ('1', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('2', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('3', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('4', '10004', 'Senior Engineer', '1995-12-03', '9999-01-01'),
('5', '10001', 'Senior Engineer', '1986-06-26', '9999-01-01'),
('6', '10002', 'Staff', '1996-08-03', '9999-01-01'),
('7', '10003', 'Senior Engineer', '1995-12-03', '9999-01-01');  
```

答案：

```sql
ALTER TABLE titles_test RENAME TO titles_2017
```

------

46、  在audit表上创建外键约束，其emp_no对应employees_test表的主键id。

```sql
CREATE TABLE employees_test(
ID INT PRIMARY KEY NOT NULL,
NAME TEXT NOT NULL,
AGE INT NOT NULL,
ADDRESS CHAR(50),
SALARY REAL
);

CREATE TABLE audit(
EMP_no INT NOT NULL,
create_date datetime NOT NULL
);

```

答案：

```sql

DROP TABLE audit;
CREATE TABLE audit(
    EMP_no INT NOT NULL,
    create_date datetime NOT NULL,
    FOREIGN KEY(EMP_no) REFERENCES employees_test(ID));

```

------

47、create view emp_v as select * from employees where emp_no >10005;
如何获取emp_v和employees有相同的数据？

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

答案：

```sql
-- 方法一
SELECT * FROM emp_v 
-- 方法二
SELECT ev.* FROM emp_v ev INNER JOIN employees e
WHERE e.emp_no = ev.emp_no
-- 方法三
SELECT * FROM employees WHERE emp_no >10005
-- 方法四
SELECT * FROM employees INTERSECT SELECT * FROM emp_v
```

------

48、将所有获取奖金的员工当前的薪水增加10%。

```sql
create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL, PRIMARY KEY (emp_no,from_date));
```

答案：

```sql
UPDATE salaries 
SET salary = salary*1.1 
WHERE emp_no IN (
    SELECT emp_no FROM emp_bonus
)
```

------

49、针对库中的所有表生成select count(*)对应的SQL语句

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));
CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

答案：

```sql
SELECT 
    "select count(*) from " || name || ";" AS cnts 
FROM 
    sqlite_master 
WHERE 
    type = 'table'
```

------

50、将employees表中的所有员工的last_name和first_name通过(')连接起来。

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

答案：

```sql
SELECT last_name || "'" || first_name FROM employees
```

------

51、查找字符串'10,A,B' 中逗号','出现的次数cnt。

答案：

```sql
SELECT length('10,A,B') - length( REPLACE('10,A,B', ',', '') ) cnt
```

------

52、获取Employees中的first_name，查询按照first_name最后两个字母，按照升序进行排列

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

答案：

```sql
-- 方法一
SELECT 
    first_name 
FROM
    employees
ORDER BY substr(first_name,length(first_name)-1)
-- 方法二
SELECT 
    first_name 
FROM
    employees
ORDER BY substr(first_name,-2)
```

------

53、按照dept_no进行汇总，属于同一个部门的emp_no按照逗号进行连接，结果给出dept_no以及连接出的结果employees

```sql
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
```

答案：

```sql
/** 
group_concat(x[,y]) 
该函数返回一个字符串，其中X是要连接的字段，Y是连接时用的符号，可省略，默认为逗号。
group_concat（）函数要和group by语句同时使用才能产生效果
**/
SELECT dept_no, group_concat(emp_no) AS employees
FROM dept_emp GROUP BY dept_no
```

------

54、查找排除当前最大、最小salary之后的员工的平均工资avg_salary。

```sql
CREATE TABLE salaries ( emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

答案：

```sql
SELECT
    AVG(salary) avg_salary
FROM
    salaries
WHERE
    to_date = '9999-01-01'
AND
    salary <> (SELECT MAX(salary) FROM salaries)
AND
    salary <> (SELECT MIN(salary) FROM salaries)
```

------

55、分页查询employees表，每5行一页，返回第2页的数据

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

答案：

```sql
-- 方法一
SELECT * FROM employees LIMIT 5,5
-- 方法二    
SELECT * FROM employees LIMIT 5 OFFSET 5
```

------

56、获取所有员工的emp_no、部门编号dept_no以及对应的bonus类型btype和received，没有分配具体的员工不显示

```sql
CREATE TABLE `dept_emp` ( 
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL, PRIMARY KEY (emp_no,from_date));
```

答案：

```sql
SELECT 
    e.emp_no, de.dept_no, eb.btype, eb.recevied
FROM 
    dept_emp de INNER JOIN employees e
ON
    de.emp_no = e.emp_no LEFT JOIN emp_bonus eb
ON
    eb.emp_no = e.emp_no
```

------

57、使用含有关键字exists查找未分配具体部门的员工的所有信息。

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
```

答案：

```sql
SELECT  * FROM employees ee
WHERE NOT exists (SELECT emp_no FROM dept_emp de where ee.emp_no = de.emp_no)
```

------

58、存在如下的视图：获取employees中的行数据，且这些行也存在于emp_v中。注意不能使用intersect关键字。
输出格式:

```sql
create view emp_v as select * from employees where emp_no >10005;
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

输出格式:

| emp_no | birth_date | first_name | last_name | gender | hire_date  |
| ------ | ---------- | ---------- | --------- | ------ | ---------- |
| 10006  | 1953-04-20 | Anneke     | Preusig   | F      | 1989-06-02 |
| 10007  | 1957-05-23 | Tzvetan    | Zielinski | F      | 1989-02-10 |
| 10008  | 1958-02-19 | Saniya     | Kalloufi  | M      | 1994-09-15 |
| 10009  | 1952-04-19 | Sumant     | Peac      | F      | 1985-02-18 |
| 10010  | 1963-06-01 | Duangkaew  | Piveteau  | F      | 1989-08-24 |
| 10011  | 1953-11-07 | Mary       | Sluis     | F      | 1990-01-22 |

答案：

```sql
-- 方法一
SELECT * FROM emp_v
-- 方法二
SELECT  * FROM employees ee
WHERE exists (SELECT emp_no FROM emp_v de where ee.emp_no = de.emp_no)
-- 方法三
SELECT * FROM employees ee 
-- 方法四
```

------

59、按照salary的累计和running_total，其中running_total为前两个员工的salary累计和，其他以此类推。 具体结果如下Demo展示。

```sql
CREATE TABLE `salaries` ( `emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));
```

输出格式:

| emp_no | salary | running_total |
| ------ | ------ | ------------- |
| 10001  | 88958  | 88958         |
| 10002  | 72527  | 161485        |
| 10003  | 43311  | 204796        |
| 10004  | 74057  | 278853        |
| 10005  | 94692  | 373545        |
| 10006  | 43311  | 416856        |
| 10007  | 88070  | 504926        |
| 10009  | 95409  | 600335        |
| 10010  | 94409  | 694744        |
| 10011  | 25828  | 720572        |

答案：

```sql
-- 方法一
SELECT 
    s1.emp_no, s1.salary, SUM(s2.salary) running_total 
FROM salaries s1, salaries s2 
WHERE s1.emp_no >= s2.emp_no
AND s1.to_date = '9999-01-01'
AND s2.to_date = '9999-01-01'
GROUP BY s1.emp_no
ORDER BY s1.emp_no
-- 方法二
SELECT 
    s1.emp_no, s1.salary, (
        SELECT SUM(s2.salary) 
        FROM salaries s2
        WHERE s2.emp_no <= s1.emp_no 
        AND s2.to_date = '9999-01-01'
    ) running_total
FROM salaries s1
WHERE s1.to_date = '9999-01-01'
GROUP BY s1.emp_no
ORDER BY s1.emp_no
```

------

60、对于employees表中，给出奇数行的first_name

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

输出格式:

| first_name |
| ---------- |
| Georgi     |
| Chirstian  |
| Anneke     |
| Tzvetan    |
| Saniya     |
| Mary       |

答案：

```sql
SELECT s1.first_name FROM employees s1
WHERE (SELECT COUNT(*) FROM employees s2 WHERE s1.first_name <= s2.first_name ) % 2 = 1
```

------

61、获取有奖金的员工相关信息。

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL, PRIMARY KEY (`emp_no`,`from_date`));
给出emp_no、first_name、last_name、奖金类型btype、对应的当前薪水情况salary以及奖金金额bonus。 bonus类型btype为1其奖金为薪水salary的10%，btype为2其奖金为薪水的20%，其他类型均为薪水的30%。 当前薪水表示to_date='9999-01-01'
```

输出格式:

| emp_no | first_name | last_name | btype | salary | bonus   |
| ------ | ---------- | --------- | ----- | ------ | ------- |
| 10001  | Georgi     | Facello   | 1     | 88958  | 8895.8  |
| 10002  | Bezalel    | Simmel    | 2     | 72527  | 14505.4 |
| 10003  | Parto      | Bamford   | 3     | 43311  | 12993.3 |
| 10004  | Chirstian  | Koblick   | 1     | 74057  | 7405.7  |

答案：

```sql
SELECT 
    e.emp_no, e.first_name, e.last_name, eb.btype, s.salary, (s.salary * eb.btype / 10.0)bonus
FROM 
    employees e INNER JOIN salaries s ON e.emp_no = s.emp_no
INNER JOIN emp_bonus eb ON
    s.emp_no = eb.emp_no
AND s.to_date = '9999-01-01'
```

------



语法总结：

- 自然连接(natural join)

> 两张表中字段名和数据类型都相同的字段进行等值连接，并返回符合条件的结果 

示例

```sql
-- e.emp_no=d.emp_no
SELECT e.last_name, e.first_name, d.detp_no
FROM employees AS e NATURAL JOIN dept_emp AS d;
```



INNER JOIN 

> 关键字在表中存在至少一个匹配时返回行。如果 "Persons" 中的行在 "Orders" 中没有匹配，就不会列出这些行。

示例

```sql
SELECT e.last_name, e.first_name, d.detp_no
FROM employees AS e INNER JOIN dept_emp AS d 
ON e.emp_no=d.emp_no
```



LEFT JOIN

> LEFT JOIN 关键字会从左表 (Persons) 那里返回所有的行，即使在右表 (Orders) 中没有匹配的行。

示例

```sql
SELECT column_name(s)
FROM table_name1
LEFT JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
```



RIGHT JOIN

> RIGHT JOIN 关键字会从右表 (Orders) 那里返回所有的行，即使在左表 (Persons) 中没有匹配的行。

示例

```sql
SELECT column_name(s)
FROM table_name1
RIGHT JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
```



FULL JOIN

> FULL JOIN 关键字会从左表 (Persons) 和右表 (Orders) 那里返回所有的行。如果 "Persons" 中的行在表 "Orders" 中没有匹配，或者如果 "Orders" 中的行在表 "Persons" 中没有匹配，这些行同样会列出。

示例

```sql
SELECT column_name(s)
FROM table_name1
FULL JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
```



distinct 和 group by 比较：

>  1、在记录少并建立了索引的情况下：distinct 比 group by 快

























***习题来自于牛客网**







