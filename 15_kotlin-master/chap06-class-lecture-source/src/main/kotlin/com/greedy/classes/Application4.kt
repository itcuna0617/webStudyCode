package com.greedy.classes

/* 추가 보조 생성자 정의 */
class Person5(var name: String, var age: Int, val gender: String) {

    var job: String = "Unknown"
    var salary: Int? = null

    constructor(name: String, age: Int, gender: String, job: String): this(name, age, gender) {
        println("기본적으로 이름, 나이, 성별을 초기화하고 직업까지 정의하는 생성자 호출")
        this.job = job
    }

    constructor(name: String, age: Int, gender: String, job: String, salary: Int): this(name, age, gender, job) {
        println("기본적으로 이름, 나이, 성별, 직업을 초기화하고 급여까지 정의하는 생성자 호출")
        this.salary = salary
    }
}

fun main(args: Array<String>) {
    var p5 = Person5("김철수", 20, "남성")
    println("${p5.name}, ${p5.age}, ${p5.gender}, ${p5.job}, ${p5.salary}")

    p5 = Person5("이영희", 30, "여성", "의사")
    println("${p5.name}, ${p5.age}, ${p5.gender}, ${p5.job}, ${p5.salary}")

    p5 = Person5("날두", 37, "남성", "축구선수", 2000000000)
    println("${p5.name}, ${p5.age}, ${p5.gender}, ${p5.job}, ${p5.salary}")
}