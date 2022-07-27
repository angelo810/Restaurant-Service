import Swal from "sweetalert2";
import http from "../http-common";
import IRecipeData from "../models/Recipe";

const create = async (data: IRecipeData) => {    
  try {
    const response = await http.post<IRecipeData>("/recipes", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La receta ha sido creado correctamente',
        confirmButtonText: 'Aceptar'    

      });
    }
    console.log(response);
  } catch (err) {
    console.log(err);
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
};

const retrieve = async (id: number) => {
    return http.get<IRecipeData>(`/recipes/${id}`);
};

const update = async (data: IRecipeData) => {
  try {    
    const response = await http.put<IRecipeData>(`/recipes/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La receta ha sido actualizado',
        confirmButtonText: 'Aceptar'    
      });
    }

  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
    
};

const remove = async (id: number) => {
    try {
      const response = await  http.delete<string>(`/recipes/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'La receta ha sido eliminada',
          confirmButtonText: 'Aceptar'    
        });
      }
    } catch (error) {
      Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    }

};

const list = (page: number, size: number, sort? : String) => {
  const urlRequest : string = "/recipes/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<IRecipeData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/recipes/count");
  return response.data;
};

const RecipeService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count

};
export default RecipeService;

