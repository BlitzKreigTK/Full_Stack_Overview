import { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import {useAuth} from "./security/AuthContext"

function LoginComponent(){
    const navigate = useNavigate();
    const [userName, setUser] = useState('Enter Username')
    const [password, setPass] = useState('')
    const [errorMsg, setErrorMsg] = useState(false)
    const authContext = useAuth()
    
    function handleUserNameChange(event){ 
        setUser(event.target.value)
    }

    function handlePasswordChange(event){
        setPass(event.target.value)
    }

    async function handleLogin(){
        if(await authContext.login(userName, password) === true){
            navigate(`/welcome/${userName}`) // When we need to take some param from url instead of '' use `` (tick)
        }
        else{
            setErrorMsg(true)
        }
    }
    return(
        <div className='LoginComponent'>
            <h1>Login</h1>
            {errorMsg && <div className="showErrorLoginMsg">Authentication failed, Please check your credentials</div>}
            <div>
                <label>Username</label>
                <input type="text" className="username" value={userName} onChange={handleUserNameChange}/>
            </div>
            <div>
                <label>Password</label>
                <input type="password" className="password" value={password} onChange={handlePasswordChange}/>
            </div>
            <div>
                <button type="submit" onClick={handleLogin}>Login</button>
            </div>
        </div>
    )
}

export default LoginComponent