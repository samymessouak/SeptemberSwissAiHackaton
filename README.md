# 🏔️ SwissResilienceAI – Hiking Safety Assistant

An **AI-powered hiking safety assistant** developed for the **September 2025 Swiss AI Hackathon**.  
Our solution helps hikers in the Swiss Alps anticipate and respond to **extreme weather events** such as glacier collapses, landslides, floods, and heatwaves.  

---

## 🌍 Problem

The Swiss Alps are increasingly vulnerable to climate change.  
- ❄️ **Glacier collapses** (e.g., Blatten tragedy)  
- 🌊 **Floods and landslides** after heavy rainfall  
- ☀️ **Heatwaves and droughts** impacting hikers’ health  
- 🌪️ **Storms and falling trees** threatening safety on trails  

Hiking is one of Switzerland’s most popular activities, but hikers often lack **real-time risk awareness**.  
**How can we make hiking safer and more resilient against extreme weather events?**

---

## 💡 Our Solution

We designed an **AI-powered mobile/web app** that acts as a **hiking safety companion**.  

🔹 **Key features**  
- 📡 **Real-time hazard alerts**: Weather and terrain-based risk warnings using user GPS location  
- 🗺️ **Dynamic trail risk maps**: Visualize safe vs. risky routes with a color-coded “Trail Safety Index”  
- 🤖 **AI recommendations**: Suggest alternative routes or mitigation measures in case of risk  
- 📴 **Offline mode**: Pre-downloaded maps and risk data for areas without connectivity  
- 🛡️ **Future extension**: Integration with parametric insurance (rescue vouchers, water supply, transport compensation)  

---

## 🏗️ Architecture / Tech Stack

- **Frontend**: React Native / Web (for cross-platform hiking app)  
- **Backend**: Python (FastAPI or Flask)  
- **AI/ML**: Hazard prediction models using weather + topographic data  
- **Data Sources**:  
  - MeteoSwiss API (real-time weather)  
  - Topographic maps & glacier datasets  
  - OpenStreetMap hiking trails  
- **Deployment**: Docker + GitHub Actions for CI/CD  

---
