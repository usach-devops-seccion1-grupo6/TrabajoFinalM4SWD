package com.devops.dxc.devops.rest;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rest/msdxc")
public class RestData {
	
	//private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	@GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getData(@RequestParam(name = "ahorro") Long ahorro) {
		return Util.getDxc(ahorro);
	}

	@GetMapping(path = "/saldo", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getSaldo(@RequestParam(name = "ahorro") Long ahorro, @RequestParam(name = "sueldo") Long sueldo) {
		return Util.saldoRestante(ahorro, sueldo);
	}

	@GetMapping(path = "/impuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public long impuesto(@RequestParam(name = "ahorro") Long ahorro, @RequestParam(name = "sueldo") Long sueldo) {
		return Util.getImpuesto(sueldo, ahorro);
	}

	@GetMapping(path = "/uf", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getUf() {
		return Util.getUf();
	}

	@GetMapping(path = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Dxc getData(@RequestParam(name = "sueldo") Long sueldo, @RequestParam(name = "ahorro") Long ahorro){
        Dxc response = new Dxc();
		response.setImpuesto(Util.getImpuesto(sueldo, ahorro));
		response.setSaldo(Util.saldoRestante(ahorro, sueldo));
		response.setDxc(Util.getDxc(ahorro));
		return response;
	}
}