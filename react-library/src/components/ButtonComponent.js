import PropTypes from 'prop-types';

export default function ButtonComponent({ text, onClick, color, disabled }) {
    return (<button onClick={onClick} disabled={disabled} style={{ backgroundColor: color }}>{text}</button>)
}

ButtonComponent.PropTypes = 
{
    text : PropTypes.string,
    onClick : PropTypes.func,
    color : PropTypes.string,
    disabled : PropTypes.bool,
}