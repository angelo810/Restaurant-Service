import Swal from "sweetalert2";
import http from "../http-common";
import IFoodData from "../models/Food";

const create = async (data: IFoodData) => {    
  try {
    const response = await http.post<IFoodData>("/foods", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'La comida ha sido creado correctamente',
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
    return http.get<IFoodData>(`/foods/${id}`);
};
const update = async (data: IFoodData) => {
    try {    
      const response = await http.put<IFoodData>(`/foods/${data.id}`, data);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'El orderen ha sido actualizado',
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
      const response = await  http.delete<string>(`/foods/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'El orderen ha sido eliminado',
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
  const urlRequest : string = "/foods/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<IFoodData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/foods/count");
  return response.data;
};

const FoodService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count

};
export default FoodService;

