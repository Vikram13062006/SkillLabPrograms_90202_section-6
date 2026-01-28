const db = require("../config/db");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

const SECRET_KEY = "1313";

// Register
exports.register = async (req, res) => {
  const { name, email, password, role_id } = req.body;

  const hashedPassword = await bcrypt.hash(password, 10);

  const sql = `INSERT INTO users (name, email, password, role_id) VALUES (?, ?, ?, ?)`;

  db.query(sql, [name, email, hashedPassword, role_id], (err, result) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: "User registered successfully!" });
  });
};

// Login
exports.login = (req, res) => {
  const { email, password } = req.body;

  const sql = `SELECT * FROM users WHERE email = ?`;
  db.query(sql, [email], async (err, results) => {
    if (err) return res.status(500).json({ error: err.message });

    if (results.length === 0)
      return res.status(400).json({ error: "User not found" });

    const user = results[0];

    const validPassword = await bcrypt.compare(password, user.password);
    if (!validPassword)
      return res.status(400).json({ error: "Invalid password" });

    const token = jwt.sign(
      { user_id: user.user_id, role_id: user.role_id },
      SECRET_KEY,
      { expiresIn: "1h" }
    );

    res.json({ message: "Login successful", token });
  });
};
