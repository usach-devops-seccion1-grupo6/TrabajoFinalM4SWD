# Api

Puerto: 9091

## Endpoints

* Diez por ciento:
    * URI: /rest/msdxc/dxc?ahorro={numero}
    * Respuesta: integer; ejemplo: 1000000
* Saldo:
    * URI: /rest/msdxc/saldo?ahorro={numero}&sueldo={numero}
    * Respuesta: integer; ejemplo: 500000
* Impuesto:
    * URI: /rest/msdxc/impuesto?sueldo={numero}
    * Respuesta: string; ejemplo: Si
* UF:
    * URI: /rest/msdxc/uf
    * Respuesta: integer; ejemplo: 38292
* Todo:
    * URI: /rest/msdxc/todo?sueldo={numero}&ahorro={numero}
    * Respuesta: json; ejemplo: <code>{
        "dxc": 1000000, 
        "saldo": 200000, 
        "impuesto": "No",
        "sueldo": 1200000,
        "ahorro": 1200000
        }
    </code>