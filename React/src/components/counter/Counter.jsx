import { useState } from 'react';
import CounterButton from './CounterButton';

export default function Counter(){
    const[count, setCount] = useState(0);

    function incrementFunctionParent(num){ 
        setCount(count+num);
    }

    function decrementFunctionParent(num){
        setCount(count-num);
    }

    function resetCounterFunction(){
        setCount(0);
    }

    return(
        <div>
            <span className="totalCount">{count}</span>
            <CounterButton num={1} incrementMethod={incrementFunctionParent} decrementMethod={decrementFunctionParent}/>
            <CounterButton num={2} incrementMethod={incrementFunctionParent} decrementMethod={decrementFunctionParent}/>
            <CounterButton num={5} incrementMethod={incrementFunctionParent} decrementMethod={decrementFunctionParent}/>
            <button className="resetbtn" onClick={resetCounterFunction}>Reset</button>
        </div>
    )
}