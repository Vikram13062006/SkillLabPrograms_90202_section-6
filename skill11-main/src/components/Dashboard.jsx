import React from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  return (
    <div>
      <h1>News Portal Dashboard</h1>

      <nav>
        <Link to="/local">Local Users</Link>
        <br />
        <Link to="/users">Users API</Link>
        <br />
        <Link to="/posts">Fake API Posts</Link>
      </nav>
    </div>
  );
}

export default Dashboard;