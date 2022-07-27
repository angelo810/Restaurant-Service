import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import IRecipeModel from '../../models/Recipe';
import RecipeService from '../../services/RecipeServices';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const RecipeList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [recipes, setRecipes] = useState<Array<IRecipeModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listRecipes(0, itemsPerPage);           
      }, []);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listRecipes(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listRecipes = (page: number, size: number) => {
       RecipeService.list(page, size)
         .then((response: any) => {
           setRecipes(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      RecipeService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeRecipe = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar la receta?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                RecipeService.remove(id)
                .then((response: any) => {
                  listRecipes(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} recetas</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre de la Receta</th>
                            <th>Ingredientes</th>
                            <th>Preparación</th>
                            <th>Observaciones</th>

                            <th>
                              <Link to={"/recipes/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {recipes && recipes.map((Recipe, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Recipe.name}</td>
                                <td>{Recipe.ingredients}</td>
                                <td>{Recipe.prepare} </td>
                                <td>{Recipe.observations} </td>
                                <td>
                                <div className="btn-group" role="group">
                                <Link to={"/recipes/retrieve/" + Recipe.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/recipes/update/" + Recipe.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeRecipe(Recipe.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination "
                  breakLabel="..."
                  pageClassName="page-link" 
                  nextClassName="page-link text-dark"
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

