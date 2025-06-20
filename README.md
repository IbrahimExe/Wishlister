# 🎮 Wishlister
 designed for gamers to track, rate, review, and discuss games they've played / would like to play, be it indie or AAA!.

**Wishlister** is a sleek, Steam‑themed mobile app built with **Kotlin** and **Android Studio**, A cataloging app designed for gamers to track, rate, and review their personal game collection, played or soon to be, AAA or indie!  
> ⚠️ *This is a student project to practice Kotlin, Firebase, and Android UI/UX.*

---

## ✨ Key Features

### Home & Collection View  
On launch, you arrive at **Your Collection**:  
- A two‑column grid of tall, CD‑case‑style cards showing each game’s cover art made using Recycler Views.  
- If your collection is empty, a friendly message prompts you to add games.

![Wishlister_Intro](https://github.com/user-attachments/assets/b0f2187d-3e74-48b5-8937-bb41caf2f096)
<!-- 🎥 Video: Showcase scrolling the collection view, tapping empty‑state message, and the polished grid layout. -->

### Search & Add Games  
Tap **Search Games** to open a live‑search screen:  
- Type to filter 100+ popular and indie titles in real time.  
- Tap a result to add it instantly to your collection (marked “unplayed” by default).

![Wishlister_Search](https://github.com/user-attachments/assets/4dc0ae7a-4f32-401c-8ae7-97143fff7c5e)
<!-- 🎥 Video: Demonstrate typing “mi” to filter “Minecraft,” tapping to add, and seeing it appear in Your Collection. -->

### Game Detail & Reviews  
Select any game card to view its **Detail** screen:  
- **Controller icon** toggles between unplayed (white) and played (green).  
- **Review box** lets you type and save short notes.  
- **Remove** button deletes the game (and its review) from your collection.  
- The background blends the game’s cover art into a Steam‑style gradient.

![Wishlister_GameDetail](https://github.com/user-attachments/assets/a302a73c-87b0-46c3-887b-23678e2bc106)
<!-- 🎥 Video: Show toggling played status, writing a review, then removing the game and returning to the grid. -->

### Polished UI & Animations  
- Steam‑inspired **gradient backgrounds**, **rounded cards**, and **pill‑shaped buttons**.  
- **Logo fade‑in** animation on the login screen.  
- Adaptive layouts ensure everything looks great on phones of all sizes.

![Wishlister_Outro](https://github.com/user-attachments/assets/caae074a-04f7-4603-9086-887f5df341e5)
<!-- 🎥 Video: Record the login screen fade‑in, then tap through to Your Collection to highlight UI consistency. -->

---

![FirebaseLogo](https://github.com/user-attachments/assets/f0d6e1e1-ea8b-427f-91ce-9a9bac84e0d4)
## Firebase Integration

- **Authentication** (email login stub / anonymous testing for this demo).  
- **Realtime Database** under `/users/{uid}/games` stores:  
  - Game ID, name, played status, and review.  
- Live listeners keep your UI in sync whenever data changes.

![Wishlister_Firebase](https://github.com/user-attachments/assets/9bf4465b-ca2a-4a97-bcd0-4ab4f9d6f3e3)

---

## Learning Outcomes

Through building Wishlister, I gained hands‑on experience with:

- **Kotlin fundamentals:** data classes, null safety, ViewBinding, and Activity lifecycles  
- **RecyclerView & Adapters:** creating custom view holders, handling click events, and grid layouts  
- **UI/UX Design:** crafting custom drawables, gradient backgrounds, pill‑shaped buttons, and responsive layouts  
- **Firebase Integration:** setting up Realtime Database listeners, writing and reading structured data, and basic Authentication flows  
- **Animations & Transitions:** implementing fade‑in effects and visual polish for a more engaging user experience  
- **Debugging & Data Handling:** filtering out null or orphaned entries, handling async callbacks, and improving app stability.
