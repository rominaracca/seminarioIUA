(function() {
    'use strict';

    angular
        .module('seminario')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['categoriesService'];

    /* @ngInject */
    function CategoryCtrl(categoriesService) {
        var vm = this;

       // vm.noti_pendientes = null;
        
        //vm.seleccionarTarea = seleccionarTarea;

        activate();

        function activate() {

        	categoriesService.list()
        	.then(
        			function(resp){
        				console.log(resp.data);
        				//$log.log(resp);
        				//$scope.productos = resp.data;
        			},
        			function(respErr){
        				//$log.log(respErr);
        			}
        	);
        	
          vm.noti_pendientes = [
            {
              id: "01",
              usuario: "Nelson Rios",
              usuario_imagen: "http://lorempixel.com/50/50/people?1",
              evento: "Solicitud",
              fecha: "15/06/2016",
              org_unit: "Facultad de Artes"
            },
            {
              id: "02",
              usuario: "Esteban Agüero",
              usuario_imagen: "http://lorempixel.com/50/50/people?2",
              evento: "Cancelación",
              fecha: "15/06/2016",
              org_unit: "Facultad de Odontología"
            },
            {
              id: "03",
              usuario: "Ulises Agüero",
              usuario_imagen: "http://lorempixel.com/50/50/people?9",
              evento: "Solicitud",
              fecha: "15/06/2016",
              org_unit: "Facultad de Psicología"
            },
            {
              id: "04",
              usuario: "Roman Hernandez",
              usuario_imagen: "http://lorempixel.com/50/50/people?3",
              evento: "Modificación",
              fecha: "15/06/2016",
              org_unit: "Facultad de Ciencias Médicas",
              org_unit_destino: "Facultad de Ciencias Químicas"
            }
          ];

       

        }

        function seleccionarTarea(idSelectedNotification) {
         
        }


    }
})();

