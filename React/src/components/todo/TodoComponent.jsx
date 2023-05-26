import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { findTodoByIdApi, updateTodoByIdApi, createNewTodoByIdApi } from "./restApi/CallingAxiosApi";
import { useAuth } from "./security/AuthContext";
import {Formik, Form, Field, ErrorMessage} from 'formik'; 
/* eslint-disable */
export default function TodoComponent(){
    
    const {id} = useParams() // To get todo.id from TodosListComponent from navigate param
    const authContext = useAuth()
    const userName = authContext.userName // Get userName from authContext
    const[description, setDescription] = useState('')
    const[targetDate, setTargetDate] = useState('')
    const navigate = useNavigate()
    
    useEffect(() => retrieveTodoById(), [id])
    
    function retrieveTodoById(){
        findTodoByIdApi(userName, id)            
            .then(response => {
                console.log(response.data)
                setDescription(response.data.description)
                setTargetDate(response.data.targetDate)
            })
            .catch(error => console.log(error))
    }
    function onSave(values){
        const todo = {
            id: id,
            username: userName,
            description: values.description,
            targetDate: values.targetDate,
            done: false
        }
        if(id == -1){
            createNewTodoByIdApi(userName, todo)
                .then(navigate(`/todosList`))
                .catch(error => console.log(error))
        }else{
            updateTodoByIdApi(id, userName, todo)
                .then(navigate(`/todosList`))
                .catch(error => console.log(error))
        }
    }

    function validate(values){
        let errors = {}
        if(values.description.length < 5){
            errors.description = "Description should be atleast 5 characters"
        }

        if(values.targetDate.length == null){
            errors.targetDate = "Target Date could not be empty"
        }
        return errors
    }

    return(
        <div className="container">
            <h1>Enter Todo Details</h1>
            <div>
                <Formik 
                    initialValues={{description, targetDate}} 
                    enableReinitialize={true}  
                    onSubmit={onSave} 
                    validate={validate} 
                    validateOnChange={false} 
                    validateOnBlur={false} >{
                    (props) => ( 
                                <Form className="form-group">
                                    <ErrorMessage name="description" component="div" className="alert alert-warning"/>
                                    <ErrorMessage name="targetDate" component="div" className="alert alert-warning"/>
                                        <fieldset>
                                            <label>Description</label>
                                            <Field type="text" name="description" className="form-control"/>
                                        </fieldset>
                                        <fieldset className="form-group">
                                            <label>Target Date</label>
                                            <Field type="date" name="targetDate" className="form-control"/>
                                        </fieldset>
                                        <div>
                                            <button className="m-5 p-2 btn btn-success" type="submit">Save</button>
                                        </div>
                                </Form> 
                            )
                        } 
                </Formik>
            </div>
        </div>
    )
}