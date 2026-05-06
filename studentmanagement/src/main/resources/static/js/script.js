const API_URL = "http://localhost:8080/students";

// Load students on page load
window.onload = function () {
    loadStudents();
};

// Get all students
function loadStudents() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            let table = "";
            data.forEach(student => {
                table += `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.course}</td>
                        <td>${student.email}</td>
                        <td>${student.marks}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
            document.getElementById("studentTable").innerHTML = table;
        });
}

// Add student
function addStudent() {
    const student = {
        name: document.getElementById("name").value,
        course: document.getElementById("course").value,
        email: document.getElementById("email").value,
        marks: document.getElementById("marks").value
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(student)
    })
    .then(() => {
        loadStudents();
    });
}

// Delete student
function deleteStudent(id) {
    fetch(API_URL + "/" + id, {
        method: "DELETE"
    })
    .then(() => {
        loadStudents();
    });
}