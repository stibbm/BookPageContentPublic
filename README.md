<div align="center">

# 📖 Book Page Content

**A robust backend service for managing books, chapters, images, videos, and content search**

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)

---

[📐 Design](#-design) •
[📚 Books API](#-books-api) •
[📑 Chapters API](#-chapters-api) •
[🖼️ Images API](#️-images-api) •
[🎬 Videos API](#-videos-api) •
[🔍 Search API](#-search-api)

</div>

---

## ✨ Features

- 📚 **Book Management** — Create and browse books with pagination and sorting
- 📑 **Chapter Organization** — Flexible chapter creation and management
- 🖼️ **Image Handling** — Upload and retrieve images by book and chapter
- 🎬 **Video Support** — Full video content management per chapter
- 🔍 **Powerful Search** — Search books by content and tags
- 👤 **Account System** — User account creation and management
- 🏆 **Milestone Tracking** — Track and update user milestones

---

## 📐 Design

### High-Level Overview

<img width="100%" alt="OverviewDiagram_v4" src="https://github.com/user-attachments/assets/7a2157d6-41c4-492f-97e3-9c9898cec451" />

### System Architecture

<img width="100%" alt="ArchitectureDiagram" src="https://github.com/user-attachments/assets/7c54298a-6cd0-4cfb-acc4-667efa35df81" />

### Entity-Relationship Diagram

<img width="100%" alt="ERDiagram" src="https://github.com/user-attachments/assets/96cbe857-91d7-4841-bba7-0c9318674b22" />

---

## 📡 API Reference

### 📚 Books API

<details>
<summary><b>BookController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/getAllBooksPaged` | Retrieve all books with pagination |
| `GET` | `/getAllBookCardsPaged` | Get book cards with pagination |
| `GET` | `/getAllBookCardsSortedPaged` | Get sorted book cards with pagination |
| `POST` | `/createBook` | Create a new book |

</details>

<details>
<summary><b>BookDetailsController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/getBookDetailsCardByBookName` | Get book details by name |
| `GET` | `/getBookDetailsCardByBookNameBatched` | Get multiple book details in batch |

</details>

<details>
<summary><b>PublicBookDetailsCardController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/getPublicBookDetailsCardByBookNumber` | Get public book details by book number |

</details>

<details>
<summary><b>BookViewController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/createBookView` | Record a book view |

</details>

---

### 📑 Chapters API

<details>
<summary><b>ChapterController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `POST` | `/createChapterWithSpecifiedChapterNumber` | Create chapter with specific number |
| `POST` | `/createChapter` | Create a new chapter |

</details>

<details>
<summary><b>ChapterHeaderController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/getChapterHeadersByBookName` | Get chapter headers for a book |

</details>

---

### 🖼️ Images API

<details>
<summary><b>ImageController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/getImagesByBookNameAndChapterNumberPaged` | Get images with pagination |
| `POST` | `/createImage` | Upload a new image |
| `GET` | `/getImage` | Retrieve a specific image |

</details>

---

### 🎬 Videos API

<details>
<summary><b>VideoController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/createVideo` | Create a new video |
| `GET` | `/getVideosByBookNameAndChapterNumber` | Get videos by book and chapter |
| `GET` | `/getVideo` | Retrieve a specific video |

</details>

---

### 🔍 Search API

<details>
<summary><b>SearchController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/searchBooksByBookTags` | Search books by tags |
| `GET` | `/searchBooksByContent` | Full-text content search |
| `GET` | `/searchBookCardsByBookTags` | Search book cards by tags |
| `GET` | `/searchBookCardsByContent` | Search book cards by content |

</details>

---

### 👤 Accounts API

<details>
<summary><b>AccountController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/createAccount` | Create a new user account |
| `GET` | `/getAccount` | Retrieve account details |

</details>

---

### 🏆 Milestones API

<details>
<summary><b>MilestoneController</b></summary>

| Method | Endpoint | Description |
|:------:|:---------|:------------|
| `GET` | `/updateMilestone` | Update a milestone |
| `GET` | `/getMilestone` | Get milestone details |

</details>

---

## 🚀 Quick Start

### Prerequisites

- Java 17+
- Gradle
- PostgreSQL

### Running the Service

```bash
# Clone the repository
git clone <repository-url>

# Navigate to project directory
cd BookPageContent

# Build the project
gradle build

# Run the application
gradle bootRun
```

---

<div align="center">

**Built with ☕ Java & 💚 Spring Boot**

</div>
