# 🎮 Wishlister

**Wishlister** is a sleek, Steam‑themed mobile app built with **Kotlin** and **Android Studio**, A cataloging app designed for gamers to track, rate, and review their personal game collection, played or soon to be, AAA or indie!
> ⚠️ *This is a student project built entirely from scratch to practice Kotlin, Firebase integration, and Android UI/UX design. While it reuses some core concepts I learned during my [To Do List App](https://github.com/IbrahimExe/Kotlin_ToDoList_App) project, the Wishlister app was developed independently from the ground up!*

---

## ✨ Key Features

### Home & Collection View  
On launch, you arrive at **Your Collection**:  
- A two‑column grid of tall, CD‑case‑style cards showing each game’s cover art made using Recycler Views.  
- If your collection is empty, a friendly message prompts you to add games.

<div align="center">
  <img src="https://github.com/user-attachments/assets/b0f2187d-3e74-48b5-8937-bb41caf2f096" alt="Wishlister Intro" width="350">
</div>
<!-- 🎥 Video: Showcase scrolling the collection view, tapping empty‑state message, and the polished grid layout. -->

### Search & Add Games  
Tap **Search Games** to open a live‑search screen:  
- Type to filter 100+ popular and indie titles in real time.  
- Tap a result to add it instantly to your collection (marked “unplayed” by default).

<p align="center">
  <img src="https://github.com/user-attachments/assets/4dc0ae7a-4f32-401c-8ae7-97143fff7c5e" alt="Wishlister Search" width="350">
</p>
<!-- 🎥 Video: Demonstrate typing “mi” to filter “Minecraft,” tapping to add, and seeing it appear in Your Collection. -->

### Game Detail & Reviews  
Select any game card to view its **Detail** screen:  
- **Controller icon** toggles between unplayed (white) and played (green).  
- **Review box** lets you type and save short notes.  
- **Remove** button deletes the game (and its review) from your collection.  
- The background blends the game’s cover art into a Steam‑style gradient.

<p align="center">
  <img src="https://github.com/user-attachments/assets/a302a73c-87b0-46c3-887b-23678e2bc106" alt="Wishlister Game Detail" width="350">
</p>
<!-- 🎥 Video: Show toggling played status, writing a review, then removing the game and returning to the grid. -->

### Polished UI & Animations  
- Steam‑inspired **gradient backgrounds**, **rounded cards**, and **pill‑shaped buttons**.  
- **Logo fade‑in** animation on the login screen.  
- Adaptive layouts ensure everything looks great on phones of all sizes.

<p align="center">
  <img src="https://github.com/user-attachments/assets/caae074a-04f7-4603-9086-887f5df341e5" alt="Wishlister Outro" width="350">
</p>
<!-- 🎥 Video: Record the login screen fade‑in, then tap through to Your Collection to highlight UI consistency. -->

---

<p align="center">
  <img src="https://github.com/user-attachments/assets/f0d6e1e1-ea8b-427f-91ce-9a9bac84e0d4" alt="Firebase Logo" width="150">
</p>

## Firebase Integration

- **Authentication** (Would create a unique username & password for each user - anonymous testing for this demo).  
- **Realtime Database** under `/users/{uid}/games` stores:  
  - Game ID, name, played status, and review tied to each unique user (So Users can log in anywhere and keep thier notes saved!).  
- Live listeners keep your UI in sync whenever data changes.

<p align="center">
  <img src="https://github.com/user-attachments/assets/9bf4465b-ca2a-4a97-bcd0-4ab4f9d6f3e3" alt="Wishlister Firebase Features">
</p>

---

## Learning Outcomes

Through building Wishlister, I gained hands‑on experience with:

- **Kotlin fundamentals:** data classes, null safety, ViewBinding, and Activity lifecycles  
- **RecyclerView & Adapters:** creating custom view holders, handling click events, and grid layouts  
- **UI/UX Design:** crafting custom drawables, gradient backgrounds, pill‑shaped buttons, and responsive layouts  
- **Firebase Integration:** setting up Realtime Database listeners, writing and reading structured data, and basic Authentication flows  
- **Animations & Transitions:** implementing fade‑in effects and visual polish for a more engaging user experience  
- **Debugging & Data Handling:** filtering out null or orphaned entries, handling async callbacks, and improving app stability.

---
## 📦 System Requirements

To run and build this project successfully, you’ll need the following setup:

### Development Tools
- **Android Studio**
- **Kotlin** or **Java** (depending on your implementation)
- **Android device or emulator** for testing

### Required Versions

| Dependency           | Version     |
|----------------------|-------------|
| `agp`                | 8.1.0       |
| `kotlin`             | 2.0.21      |
| `core-ktx`           | 1.16.0      |
| `junit`              | 4.13.2      |
| `junitVersion`       | 1.2.1       |
| `espresso-core`      | 3.6.1       |
| `appcompat`          | 1.7.1       |
| `material`           | 1.12.0      |
| `activity`           | 1.10.1      |
| `constraintlayout`   | 2.2.1       |
