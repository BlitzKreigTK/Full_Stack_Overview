import './Counter.css';
import {PropTypes} from 'prop-types';

CounterButton.propTypes = {
    num: PropTypes.number
}

CounterButton.defaultProps = {
    num:1
}

export default function CounterButton({num, incrementMethod, decrementMethod}){
    return(
        <div>
            <div>
                <button className="counterbtn" onClick={() => incrementMethod(num)}>+{num}</button>
                <button className="counterbtn" onClick={() => decrementMethod(num)}>-{num}</button>
            </div>   
        </div>
    )
}