# To-Do List (Java REST + HTML/JS) ✅

This is a full-stack to-do list web application where the backend is built with Java and the frontend uses HTML and JavaScript.  
Think of it like a digital sticky note board where the server acts as the brain storing all your notes, and the browser is the hand moving them around.

## 🧠 Features

- 📝 Add, update, delete, and view to-do tasks
- 📡 RESTful API built with Java
- 🌐 Frontend built with HTML + JavaScript
- 🔄 Real-time interaction using asynchronous requests
- 🐳 Docker-compatible
- ☕ Runs as a Java JAR file via Maven

## ⚙️ Tech Stack

| Layer | Technology |
|:--|:--|
| Backend | Java, Maven, REST API |
| Frontend | HTML, CSS, JavaScript |
| Packaging | JAR executable |
| Deployment | Docker, render.yaml |

## 🚀 How It Works

1. **Frontend** (HTML/JS) sends requests like:  
   “Hey server, add this new task: 'Buy oat milk' 🛒”

2. **Backend** (Java REST API) receives the request, stores it, and replies with a confirmation.

3. The frontend updates instantly — no need to refresh the page.

> It’s like passing messages between two smart roommates: one who handles the UI (browser) and one who manages storage (server).
> 
## 🧪 How to Run

```bash
# 1. Build the app
mvn clean package

# 2. Run the JAR
java -jar target/todolist2-jar-with-dependencies.jar

OR

docker build -t todo-app .
docker run -p 8080:8080 todo-app
