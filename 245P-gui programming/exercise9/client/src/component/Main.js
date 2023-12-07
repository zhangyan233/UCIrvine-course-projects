import "./Main.css";

function Main(props, children) {
  return (
    <>
      <div className="main">
            <article>
                <h3>{props.title}</h3>
                <figure>
                    <img className={props.title} src={props.image} alt={props.title} width="240" height="200"/>
                </figure>
                <p>{props.content}</p>
            </article>
      </div>
    </>
  );
}

export default Main;
