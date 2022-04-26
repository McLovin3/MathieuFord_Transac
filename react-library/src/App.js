import logo from './logo.svg';
import './App.css';

function App() {
  const clients = [
    {
      id: 1,
      name: 'Homer',
    },
    {
      id: 2,
      name: 'Bart',
    },
    {
      id: 3,
      name: 'Maggie',
    },
  ];

  const employees = [
    {
      id: 1,
      name: 'Fry',
    },
    {
      id: 2,
      name: 'Leela',
    },
    {
      id: 3,
      name: 'Bender',
    },
  ];

  const [users, setUsers] = useState(clients);
  const [isClients, setIsClients] = useState(true);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
