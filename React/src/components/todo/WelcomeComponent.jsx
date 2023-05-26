import {Link} from 'react-router-dom';
import { useState } from 'react';
import { retrieveHelloWorldPathVarBeanApi } from './restApi/CallingAxiosApi';
import {useAuth} from './security/AuthContext';

function WelcomeComponent(){
    const[message, setMessage] = useState(null)
    const authContext = useAuth()
    const UserName = authContext.userName

    function CallRestAPIComponent(){
        retrieveHelloWorldPathVarBeanApi(UserName)
            .then((response)=>{SucessfulResponse(response)})
            .catch((error)=>{FailedResponse(error)})
    }
    
    function SucessfulResponse(response){
        setMessage(response.data.message)
    }
    
    function FailedResponse(error){
        console.log(error)
    }

    return(
        <div>
            <div className="TodosLink">
                Manage your Todos Here : <Link to="/todosList">Manage your Todos</Link>
            </div>    
            <button className = "btn btn-success m-5" onClick={CallRestAPIComponent}>Welcome Message</button>
            <div className="text-dark">{message}</div>
        </div>
    )
}

export default WelcomeComponent