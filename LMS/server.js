const express = require("express");
const cors = require("cors");
const db = require("./config/db");

const authRoutes = require("./routes/auth");

const app = express();

app.use(cors());
app.use(express.json());
app.use("/api/auth", authRoutes);

app.get("/", (req, res) => {
  res.send("LMS Backend Running Successfully");
});

const PORT = 1500;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
