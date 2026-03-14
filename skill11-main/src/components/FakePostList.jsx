import React, { useState, useEffect } from "react";
import axios from "axios";

function FakePostList() {

  const [posts, setPosts] = useState([]);

  useEffect(() => {
    axios.get("https://dummyjson.com/posts")
      .then(res => {
        setPosts(res.data.posts);
      });
  }, []);

  return (
    <div>

      <h2>Fake API Posts</h2>

      {posts.map(post => (
        <div key={post.id}>

          <h3>{post.title}</h3>
          <p>{post.body}</p>

        </div>
      ))}

    </div>
  );
}

export default FakePostList;