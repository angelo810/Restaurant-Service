import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link , useParams} from 'react-router-dom';
import IRecipeModel from "../../models/Recipe";
import RecipeService from "../../services/RecipeServices";

export const RecipeCard = () => {
  const { id }= useParams();

  const [recipe, setRecipe] = useState<IRecipeModel>();

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

    return (
      <div>
      { 
        recipe ? (
          <div>          
          <h2>{recipe.name}</h2>
          <p>{recipe.ingredients}</p>
          <ul>
            <li>Preparación : {recipe.prepare}</li>
            <li>Observaciones : {recipe.observations}</li>
          </ul>
          <br />
              <div className="btn-group" role="group">                
                <Link to={"/recipes"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
                <button type="button" className="btn btn-danger">
                  <FaTrash />Eliminar
                </button>
              </div>
          </div>

        ) : 
        ( 
          <h1>No hay una receta activa</h1>
        )
      }
      </div>
    );
}

