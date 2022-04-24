import React from "react";

const Button = ({ text = "Button", onClick = () => { }, color = "pink" }: { text: string, onClick: any, color?: string }) => {
    return (<button onClick={onClick} style={{ backgroundColor: color }} className="btn">{text}</button>)
}

export default Button;