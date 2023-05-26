import { createContext, useState, useContext } from "react";
import { JwtAuthApi } from "../restApi/CallingAxiosApi";
import { apiClient } from "../restApi/axios";

// Create some context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

// Provide context to all children in TodoApp.jsx
export default function AuthProvider({children}){

    // Created a context which would be shared across all components
    const[isAuthenticated, setAuthenticated] = useState(false)

    const[userName, setUserName] = useState(false)

    const [authToken, setAuthToken] = useState(null)

    /*async function login(username, password){
        const token =  'Basic '+ window.btoa(username + ":" + password)
        try{
            const response = await basicAuthApi(token)
            // eslint-disable-next-line
            if(response.status == 200){
                setAuthenticated(true)
                setUserName(username)
                setAuthToken(token)
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = token
                        return config
                    }
                )
                return true
            }
            else{
                logout()
                return false
            }
        }
        catch{
            logout()
            return false
        }
    }*/
    async function login(username, password){
        try{
            const response = await JwtAuthApi(username, password)
            // eslint-disable-next-line
            if(response.status == 200){
                const jwtToken = 'Bearer '+response.data.token
                setAuthenticated(true)
                setUserName(username)
                setAuthToken(jwtToken)
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )
                return true
            }
            else{
                logout()
                return false
            }
        }
        catch{
            logout()
            return false
        }
    }
    function logout(){
        setAuthenticated(false)
        setUserName(null)
        setAuthToken(null)
    }

    return(
        <div>
            <AuthContext.Provider value = {{isAuthenticated, setAuthenticated, login, logout, userName, authToken}}>
                {children}
            </AuthContext.Provider>
        </div>
    )
}