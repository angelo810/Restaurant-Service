import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IFoodModel from "../../models/Food";
import FoodService from "../../services/FoodServices";

export const FoodForm = () => {
  
  const { id }= useParams();
  let navigate = useNavigate();

    //Modelo vacío
    const initialFoodModel : IFoodModel = {
        id: null,
        name: "",
        price:15 ,
        category: ""
    };

    //Hooks para gestionar el modelo
    const [food, setFood] = useState<IFoodModel>(initialFoodModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setFood({ ...food, [name]: value });
    };

    const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const { name, value } = event.target;
      setFood({ ...food, [name]: value });
  };

    const saveFood = () => {        
      if(food.id !== null)
      {
        FoodService.update(food)
        .then((response: any) => {
          navigate("/foods");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
        FoodService.create(food)
          .then((response: any) => {    
            navigate("/foods");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

    useEffect(() => {
      if (id)
      getFood(id);
    }, [id]);

    const getFood = (id: any) => {
      FoodService.retrieve(id)
        .then((response: any) => {
          setFood(response.data); //Víncula el resultado del servicio con la función del Hook useState
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
   };

    return ( //JSX
      <div className="submit-form">       
          <div>
            { food.id !== null ? (<h1>Orden Actualizada {food.name}</h1>) : (<h1>Registro de una nueva orden</h1>) }            
            <div className="form-group">
            <label htmlFor="name">Estado de la orden</label>
            <input
              type="text"
              placeholder="Ingrese el estado de la orden"
              className="form-control"
              id="name"
              required
              value={food.name}
              onChange={handleInputChange}
              name="name"
            />
            <label htmlFor="price">Descripcion de la Orden</label>
            <input            
              type="number"
              className="form-control"
              placeholder="Describa la Orden"
              id="price"
              required
              value={food.price}
              onChange={handleInputChange}
              name="price"
            />
            <label htmlFor="category">Categoria de comida</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Mesero a cargo"
              id="category"
              required
              value={food.category}
              onChange={handleInputChange}
              name="category"
            />
            <br />
              <div className="btn-group" role="group">                
                <Link to={"/foods"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" onClick={saveFood} className="btn btn-success">
                  <FaSave />Guardar
                </button>
              </div>
            </div>
          </div>    
              
      </div>        
    );

}

