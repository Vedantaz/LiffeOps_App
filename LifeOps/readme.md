# LifeOps Backend APIs

## 1. Get All Tasks
GET /tasks

Response:
[
  {
    "id": 1,
    "title": "Learn Spring Boot",
    "completed": false
  }
]

---

## 2. Create Task
POST /tasks

Request:
{
  "title": "Learn Redis",
  "completed": false
}
