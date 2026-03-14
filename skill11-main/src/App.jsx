import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Dashboard from "./components/Dashboard";
import LocalUserList from "./components/LocalUserList";
import UserList from "./components/UserList";
import FakePostList from "./components/FakePostList";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/local" element={<LocalUserList />} />
        <Route path="/users" element={<UserList />} />
        <Route path="/posts" element={<FakePostList />} />
      </Routes>
    </Router>
  );
}

export default App;