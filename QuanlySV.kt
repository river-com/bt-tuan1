
data class Student(
    val id: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)

fun main() {

    // ==========================================
    // 5 SINH VIEN MAU
    // ==========================================

    val students = mutableListOf(
        Student(
            "SV001",
            "Nguyen Van An",
            20,
            "Cong nghe thong tin",
            8.5
        ),

        Student(
            "SV002",
            "Tran Thi Binh",
            21,
            "Cong nghe thong tin",
            9.2
        ),

        Student(
            "SV003",
            "Le Van Cuong",
            22,
            "Khoa hoc may tinh",
            7.8
        ),

        Student(
            "SV004",
            "Pham Thi Dung",
            20,
            "Cong nghe thong tin",
            6.5
        ),

        Student(
            "SV005",
            "Hoang Van Em",
            23,
            "Ky thuat phan mem",
            9.5
        )
    )


    // ==========================================
    // MENU CHINH
    // ==========================================

    while (true) {

        println()
        println("========== QUAN LY SINH VIEN ==========")
        println("1. Them sinh vien")
        println("2. Hien thi tat ca sinh vien")
        println("3. Tim kiem sinh vien")
        println("4. Tinh GPA trung binh theo nganh")
        println("5. Tim sinh vien co GPA cao nhat")
        println("6. Xoa sinh vien")
        println("7. Dem sinh vien co GPA >= 8.0")
        println("8. Dem sinh vien co GPA < 5.0")
        println("9. Tim sinh vien lon tuoi nhat")
        println("10. Tim sinh vien co GPA tu 7.0 den 8.5")
        println("11. Tim sinh vien theo nganh")
        println("12. Tim sinh vien theo mot phan ten")
        println("13. Sap xep sinh vien theo GPA giam dan")
        println("14. Hien thi 3 sinh vien co GPA cao nhat")
        println("15. Sap xep sinh vien theo tuoi")
        println("16. Sap xep sinh vien theo ten ABC")
        println("17. Tim sinh vien theo ma sinh vien")
        println("18. Hien thi danh sach sinh vien nhan hoc bong")
        println("0. Thoat")
        println("========================================")
        print("Chon: ")

        val choice = readLine()?.toIntOrNull()

        when (choice) {

            1 -> addStudent(students)

            2 -> displayStudents(students)

            3 -> searchStudent(students)

            4 -> calculateAverageGPAByMajor(students)

            5 -> findHighestGPA(students)

            6 -> removeStudent(students)

            7 -> countGPA8(students)

            8 -> countGPALess5(students)

            9 -> findOldestStudent(students)

            10 -> findGPA7To85(students)

            11 -> findByMajor(students)

            12 -> searchByPartialName(students)

            13 -> sortByGPA(students)

            14 -> displayTop3(students)

            15 -> sortByAge(students)

            16 -> sortByName(students)

            17 -> searchByID(students)

            18 -> scholarshipStudents(students)

            0 -> {
                println("Cam on ban da su dung chuong trinh!")
                println("Chuong trinh ket thuc.")
                return
            }

            else -> {
                println("Lua chon khong hop le! Vui long chon lai.")
            }
        }
    }
}


// ==========================================================
// 1. THEM SINH VIEN
// ==========================================================

fun addStudent(students: MutableList<Student>) {

    println()
    println("========== THEM SINH VIEN ==========")

    print("Nhap ma sinh vien: ")
    val id = readLine()?.trim() ?: ""

    if (id.isEmpty()) {
        println("Ma sinh vien khong duoc de trong!")
        return
    }

    // Kiem tra ma sinh vien da ton tai
    if (students.any { it.id.equals(id, ignoreCase = true) }) {
        println("Ma sinh vien da ton tai!")
        return
    }


    print("Nhap ho va ten: ")
    val fullName = readLine()?.trim() ?: ""

    if (fullName.isEmpty()) {
        println("Ho va ten khong duoc de trong!")
        return
    }


    print("Nhap tuoi: ")
    val age = readLine()?.toIntOrNull()

    if (age == null || age <= 0) {
        println("Tuoi khong hop le!")
        return
    }


    print("Nhap nganh: ")
    val major = readLine()?.trim() ?: ""

    if (major.isEmpty()) {
        println("Nganh khong duoc de trong!")
        return
    }


    print("Nhap GPA (0 - 10): ")
    val gpa = readLine()?.toDoubleOrNull()

    // Validation GPA
    if (gpa == null) {
        println("GPA phai la mot so!")
        return
    }

    if (gpa < 0 || gpa > 10) {
        println("GPA khong hop le! GPA phai nam trong khoang tu 0 den 10.")
        return
    }


    val student = Student(
        id,
        fullName,
        age,
        major,
        gpa
    )

    students.add(student)

    println("Them sinh vien thanh cong!")
}


// ==========================================================
// 2. HIEN THI TAT CA SINH VIEN
// ==========================================================

fun displayStudents(students: List<Student>) {

    println()
    println("========== DANH SACH SINH VIEN ==========")

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang rong!")
        return
    }

    println("Tong so sinh vien: ${students.size}")

    for (student in students) {
        printStudent(student)
    }
}


// ==========================================================
// HIEN THI THONG TIN MOT SINH VIEN
// ==========================================================

fun printStudent(student: Student) {

    println("----------------------------------------")
    println("Ma sinh vien : ${student.id}")
    println("Ho va ten    : ${student.fullName}")
    println("Tuoi         : ${student.age}")
    println("Nganh        : ${student.major}")
    println("GPA          : %.2f".format(student.gpa))
}


// ==========================================================
// 3. TIM KIEM SINH VIEN
// ==========================================================

fun searchStudent(students: List<Student>) {

    println()
    println("========== TIM KIEM SINH VIEN ==========")

    print("Nhap ma sinh vien hoac ho ten: ")
    val keyword = readLine()?.trim() ?: ""

    if (keyword.isEmpty()) {
        println("Tu khoa tim kiem khong duoc de trong!")
        return
    }

    val result = students.filter {

        it.id.equals(keyword, ignoreCase = true) ||
                it.fullName.equals(keyword, ignoreCase = true)
    }


    if (result.isEmpty()) {

        println("Khong tim thay sinh vien!")

    } else {

        println("Tim thay ${result.size} sinh vien:")

        result.forEach {
            printStudent(it)
        }
    }
}


// ==========================================================
// 4. TINH GPA TRUNG BINH THEO NGANH
// ==========================================================

fun calculateAverageGPAByMajor(students: List<Student>) {

    println()
    println("========== GPA TRUNG BINH THEO NGANH ==========")

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang rong!")
        return
    }


    print("Nhap nganh can tinh GPA: ")
    val major = readLine()?.trim() ?: ""

    if (major.isEmpty()) {
        println("Nganh khong duoc de trong!")
        return
    }


    val result = students.filter {

        it.major.equals(major, ignoreCase = true)
    }


    if (result.isEmpty()) {

        println("Khong co sinh vien nao thuoc nganh \"$major\"!")

    } else {

        val averageGPA = result.map {
            it.gpa
        }.average()

        println("Nganh: $major")
        println("So sinh vien: ${result.size}")
        println("GPA trung binh: %.2f".format(averageGPA))
    }
}


// ==========================================================
// 5. TIM SINH VIEN CO GPA CAO NHAT
// ==========================================================

fun findHighestGPA(students: List<Student>) {

    println()
    println("========== SINH VIEN CO GPA CAO NHAT ==========")

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang rong!")
        return
    }


    val highestGPA = students.maxOf {
        it.gpa
    }


    val result = students.filter {
        it.gpa == highestGPA
    }


    println("GPA cao nhat: %.2f".format(highestGPA))

    result.forEach {
        printStudent(it)
    }
}


// ==========================================================
// 6. XOA SINH VIEN
// ==========================================================

fun removeStudent(students: MutableList<Student>) {

    println()
    println("========== XOA SINH VIEN ==========")

    print("Nhap ma sinh vien can xoa: ")
    val id = readLine()?.trim() ?: ""

    if (id.isEmpty()) {
        println("Ma sinh vien khong duoc de trong!")
        return
    }


    val student = students.find {

        it.id.equals(id, ignoreCase = true)
    }


    if (student == null) {

        println("Khong tim thay sinh vien co ma: $id")

    } else {

        students.remove(student)

        println("Xoa sinh vien thanh cong!")
        println("Ma sinh vien: ${student.id}")
    }
}


// ==========================================================
// 7. DEM SO SINH VIEN CO GPA >= 8.0
// ==========================================================

fun countGPA8(students: List<Student>) {

    println()
    println("========== DEM SINH VIEN GPA >= 8.0 ==========")

    val count = students.count {

        it.gpa >= 8.0
    }


    println("So sinh vien co GPA >= 8.0: $count")
}


// ==========================================================
// 8. DEM SO SINH VIEN CO GPA < 5.0
// ==========================================================

fun countGPALess5(students: List<Student>) {

    println()
    println("========== DEM SINH VIEN GPA < 5.0 ==========")

    val count = students.count {

        it.gpa < 5.0
    }


    println("So sinh vien co GPA < 5.0: $count")
}


// ==========================================================
// 9. TIM SINH VIEN LON TUOI NHAT
// ==========================================================

fun findOldestStudent(students: List<Student>) {

    println()
    println("========== SINH VIEN LON TUOI NHAT ==========")

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang rong!")
        return
    }


    val oldestAge = students.maxOf {

        it.age
    }


    val result = students.filter {

        it.age == oldestAge
    }


    println("Tuoi lon nhat: $oldestAge")

    result.forEach {
        printStudent(it)
    }
}


// ==========================================================
// 10. TIM SINH VIEN CO GPA TU 7.0 DEN 8.5
// ==========================================================

fun findGPA7To85(students: List<Student>) {

    println()
    println("========== SINH VIEN CO GPA TU 7.0 DEN 8.5 ==========")


    val result = students.filter {

        it.gpa in 7.0..8.5
    }


    if (result.isEmpty()) {

        println("Khong co sinh vien nao co GPA tu 7.0 den 8.5!")

    } else {

        println("Danh sach sinh vien:")

        result.forEach {
            printStudent(it)
        }
    }
}


// ==========================================================
// 11. TIM SINH VIEN THEO NGANH
// ==========================================================

fun findByMajor(students: List<Student>) {

    println()
    println("========== TIM SINH VIEN THEO NGANH ==========")

    print("Nhap ten nganh: ")
    val major = readLine()?.trim() ?: ""


    if (major.isEmpty()) {

        println("Ten nganh khong duoc de trong!")
        return
    }


    val result = students.filter {

        it.major.equals(major, ignoreCase = true)
    }


    if (result.isEmpty()) {

        println("Khong tim thay sinh vien thuoc nganh \"$major\"!")

    } else {

        println("Tim thay ${result.size} sinh vien:")

        result.forEach {
            printStudent(it)
        }
    }
}


// ==========================================================
// 12. TIM SINH VIEN THEO MOT PHAN TEN
// ==========================================================

fun searchByPartialName(students: List<Student>) {

    println()
    println("========== TIM KIEM THEO MOT PHAN TEN ==========")

    print("Nhap mot phan ho ten: ")
    val keyword = readLine()?.trim() ?: ""


    if (keyword.isEmpty()) {

        println("Tu khoa khong duoc de trong!")
        return
    }


    val result = students.filter {

        it.fullName.contains(
            keyword,
            ignoreCase = true
        )
    }


    if (result.isEmpty()) {

        println("Khong tim thay sinh vien!")

    } else {

        println("Tim thay ${result.size} sinh vien:")

        result.forEach {
            printStudent(it)
        }
    }
}


// ==========================================================
// 13. SAP XEP SINH VIEN THEO GPA GIAM DAN
// ==========================================================

fun sortByGPA(students: MutableList<Student>) {

    println()
    println("========== SAP XEP THEO GPA GIAM DAN ==========")


    students.sortByDescending {

        it.gpa
    }


    displayStudents(students)
}


// ==========================================================
// 14. HIEN THI 3 SINH VIEN CO GPA CAO NHAT
// ==========================================================

fun displayTop3(students: List<Student>) {

    println()
    println("========== TOP 3 SINH VIEN CO GPA CAO NHAT ==========")


    if (students.isEmpty()) {

        println("Danh sach sinh vien dang rong!")
        return
    }


    val top3 = students
        .sortedByDescending {
            it.gpa
        }
        .take(3)


    top3.forEachIndexed { index, student ->

        println()
        println("----- Xep hang ${index + 1} -----")

        printStudent(student)
    }
}


// ==========================================================
// 15. SAP XEP SINH VIEN THEO TUOI
// ==========================================================

fun sortByAge(students: MutableList<Student>) {

    println()
    println("========== SAP XEP THEO TUOI ==========")


    students.sortBy {

        it.age
    }


    displayStudents(students)
}


// ==========================================================
// 16. SAP XEP SINH VIEN THEO TEN ABC
// ==========================================================

fun sortByName(students: MutableList<Student>) {

    println()
    println("========== SAP XEP THEO TEN ABC ==========")


    students.sortBy {

        it.fullName.lowercase()
    }


    displayStudents(students)
}


// ==========================================================
// 17. TIM SINH VIEN THEO MA SINH VIEN
// ==========================================================

fun searchByID(students: List<Student>) {

    println()
    println("========== TIM THEO MA SINH VIEN ==========")

    print("Nhap ma sinh vien: ")
    val id = readLine()?.trim() ?: ""


    if (id.isEmpty()) {

        println("Ma sinh vien khong duoc de trong!")
        return
    }


    val student = students.find {

        it.id.equals(
            id,
            ignoreCase = true
        )
    }


    if (student == null) {

        println("Khong tim thay sinh vien co ma: $id")

    } else {

        println("Tim thay sinh vien:")

        printStudent(student)
    }
}


// ==========================================================
// 18. DANH SACH SINH VIEN NHAN HOC BONG
// DIEU KIEN: GPA > 9
// ==========================================================

fun scholarshipStudents(students: List<Student>) {

    println()
    println("========== DANH SACH SINH VIEN NHAN HOC BONG ==========")
    println("Dieu kien: GPA > 9.0")


    val result = students.filter {

        it.gpa > 9.0
    }


    if (result.isEmpty()) {

        println("Khong co sinh vien nao du dieu kien nhan hoc bong!")

    } else {

        println("So sinh vien nhan hoc bong: ${result.size}")

        result.forEach {
            printStudent(it)
        }
    }
}
