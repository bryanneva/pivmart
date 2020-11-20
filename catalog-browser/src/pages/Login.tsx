import * as React from "react";
import {useState} from "react";
import {Gateway} from "../domain/Gateway";

interface Props {
  gateway: Gateway,
}

export const Login: React.FC<Props> = ({gateway}) => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const login = (event: any) => {
    gateway.authApi.login(username, password)
    event.preventDefault();
  };

  return (
    <div>
      <form onSubmit={(e) => login(e)}>
        <label>username: <input type="text" value={username} onChange={(e) => setUsername(e.target.value)}/></label>
        <label>password: <input type="text" value={password} onChange={(e) => setPassword(e.target.value)}/></label>
        <button onClick={(e) => login(e)}>Log in</button>
      </form>
    </div>
  );
};
