export default function ButtonComponent({ text = "Button", onClick = () => { }, color = "pink", disabled }:
    { text: string; onClick: any; color?: string, disabled: boolean }) {
    return (<button onClick={onClick} disabled={disabled} style={{ backgroundColor: color }}>{text}</button>)
}
