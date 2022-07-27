import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IFoodModel from '../../models/Food';
import FoodService from '../../services/FoodServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const FoodList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [foods, setFoods] = useState<Array<IFoodModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    const [currentText, setCurrentText] = useState('');

    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listFoods(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listFoods(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listFoods = (page: number, size: number) => {
       FoodService.list(page, size)
         .then((response: any) => {
           setFoods(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      FoodService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeFood = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar la comida?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                FoodService.remove(id)
                .then((response: any) => {
                  listFoods(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
     function handleUpdate(e: { target: { value: any; }; }){
      let query = e.target.value
      setCurrentText(query)
      filterFoods(query)
    }
     function filterFoods(query: string){

      if(query === ""){
        setFoods(foods)
        return
      }
  
      let result = foods.filter((obj)=>
      obj.name.toLowerCase().includes(query.toLowerCase()) || obj.name.includes(query))
      
      console.log()
      setFoods(result)
      
    }

    console.log(foods);
        return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} Comidas</h1>
            <div className="input-group">
            <span className="input-group-text w-25">Datos a Buscar</span>
            <input type="text" aria-label="First name" className="form-control"
               placeholder="Ingrese datos a buscar"
               value={currentText}
               onChange={handleUpdate}

               />  
            </div>
           
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre de la Comida</th>
                            <th>Precio</th>
                            <th>Categoria de la Comida</th>
                            <th>
                              <Link to={"/foods/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {foods && foods.map((Food, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Food.name}</td>
                                <td>{Food.price}</td>
                                <td>{Food.category} </td>
                                
                                <td>
                                <div className="btn-group" role="group">
                              
                                  <button className="btn btn-danger" onClick={() => removeFood(Food.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  pageClassName="page-link" 
                  nextClassName="page-link"
                  nextLabel="Next »"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousClassName="page-link bg-red"
                  previousLabel="« Previous"/>

            </div>            
        </div>
     );
}

