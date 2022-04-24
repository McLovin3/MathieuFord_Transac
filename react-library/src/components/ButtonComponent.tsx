const ButtonComponent = ({ text = "Button", onClick = () => { }, color = "pink" }: { text: string, onClick: any, color?: string }) => {
    return (<button onClick={onClick} style={{ backgroundColor: color }}>{text}</button>)
}

export default ButtonComponent;