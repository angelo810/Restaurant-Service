import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IRecipeModel from "../../models/Recipe";
import RecipeService from "../../services/RecipeServices";

export const RecipeForm = () => {
  
  const { id }= useParams();
  let navigate = useNavigate();

    //Modelo vacío
    const initialRecipeModel : IRecipeModel = {
        id: null,
        name: "",
        ingredients:"" ,
        prepare: "",
        observations: ""

    };

    //Hooks para gestionar el modelo
    const [recipe, setRecipe] = useState<IRecipeModel>(initialRecipeModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setRecipe({ ...recipe, [name]: value });
    };

    const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const { name, value } = event.target;
      setRecipe({ ...recipe, [name]: value });
  };

    const saveRecipe = () => {        
      if(recipe.id !== null)
      {
        RecipeService.update(recipe)
        .then((response: any) => {
          navigate("/recipes");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
        RecipeService.create(recipe)
          .then((response: any) => {    
            navigate("/recipes");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

    useEffect(() => {
      if (id)
      getRecipe(id);
    }, [id]);

    const getRecipe = (id: any) => {
      RecipeService.retrieve(id)
        .then((response: any) => {
          setRecipe(response.data); //Víncula el resultado del servicio con la función del Hook useState
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
   };

    return ( //JSX
      <div className="submit-form">       
          <div>
            { recipe.id !== null ? (<h1>Receta Actualizada {recipe.name}</h1>) : (<h1>Registro de una nueva Receta</h1>) }            
            <div className="form-group">
            <label htmlFor="name">Nombre de la Receta</label>
            <input
              type="text"
              placeholder="Ingrese los ingredientes de la receta"
              className="form-control"
              id="name"
              required
              value={recipe.name}
              onChange={handleInputChange}
              name="name"
            />
            <label htmlFor="ingredients">Ingredientes de la Receta</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Describa la Orden"
              id="ingredients"
              required
              value={recipe.ingredients}
              onChange={handleInputChange}
              name="ingredients"
            />
            <label htmlFor="prepare">Preparacion de la Receta</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Forma de preparacion"
              id="prepare"
              required
              value={recipe.prepare}
              onChange={handleInputChange}
              name="prepare"
            />
            <br />
            <label htmlFor="prepare">Observaciones de la Receta</label>
            <input            
              type="text"
              className="form-control"
              placeholder="Observaciones de preparacion"
              id="observations"
              required
              value={recipe.observations}
              onChange={handleInputChange}
              name="observations"
            />
              <div className="btn-group" role="group">                
                <Link to={"/recipes"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" onClick={saveRecipe} className="btn btn-success">
                  <FaSave />Guardar
                </button>
              </div>
            </div>
          </div>        
      </div>        
    );

}

