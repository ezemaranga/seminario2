package com.seminario.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.model.Partido;
import com.seminario.model.Postulacion;
import com.seminario.partido.dto.AceptarJugadorRequest;
import com.seminario.partido.dto.AceptarJugadorResponse;
import com.seminario.partido.dto.BorrarPartidoRequest;
import com.seminario.partido.dto.BorrarPartidoResponse;
import com.seminario.partido.dto.BuscarPartidoPorOrganizadorRequest;
import com.seminario.partido.dto.BuscarPartidoPorOrganizadorResponse;
import com.seminario.partido.dto.BuscarPartidosPorHabilidadesRequest;
import com.seminario.partido.dto.BuscarPartidosPorHabilidadesResponse;
import com.seminario.partido.dto.CrearPartidoRequest;
import com.seminario.partido.dto.CrearPartidoResponse;
import com.seminario.partido.dto.PostulacionesPartidoResponse;
import com.seminario.partido.dto.PostularmePartidoRequest;
import com.seminario.partido.dto.PostularmePartidoResponse;
import com.seminario.repository.PartidoRepository;
import com.seminario.repository.PostulacionRepository;
import com.seminario.usuario.dto.GetAllPartidosResponse;

@RestController
@RequestMapping("/partidos")
public class PartidoController {
	

	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private PostulacionRepository postulacionRepository;
	
	@RequestMapping(value = "/buscarpartidoporidorg", method = RequestMethod.POST)
    public BuscarPartidoPorOrganizadorResponse buscarPartidosPorIdOrganizador(@RequestBody BuscarPartidoPorOrganizadorRequest request) {
		BuscarPartidoPorOrganizadorResponse response = new BuscarPartidoPorOrganizadorResponse();
		Partido partido = partidoRepository.findByIdUsuarioOrganizador(request.getIdUsuarioOrganizador());
		response.setPartidos(partido);
		return response;
    }
	
	@RequestMapping(value = "/buscarpartidosporhabilidades", method = RequestMethod.POST)
    public BuscarPartidosPorHabilidadesResponse buscarPartidosPorHabilidades(@RequestBody BuscarPartidosPorHabilidadesRequest request) {
		BuscarPartidosPorHabilidadesResponse response = new BuscarPartidosPorHabilidadesResponse();
		List<Partido> partidos = partidoRepository.findAll();
		HashMap<String,Integer> habilidadesEnNumeros = new HashMap<String,Integer>();
		habilidadesEnNumeros.put("ROJO", 0);
		habilidadesEnNumeros.put("AMARILLO", 1);
		habilidadesEnNumeros.put("VERDE", 2);
		for (Partido p : partidos) {
			Boolean add = true;
			HashMap<String, String> habilidadesDelPartido = p.getHabilidades();
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("estado")) > habilidadesEnNumeros.get(request.getHabilidades().get("estado")))
				add = false;
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("ataque")) > habilidadesEnNumeros.get(request.getHabilidades().get("ataque")))
				add = false;
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("ataja")) > habilidadesEnNumeros.get(request.getHabilidades().get("ataja")))
				add = false;
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("defensa")) > habilidadesEnNumeros.get(request.getHabilidades().get("defensa")))
				add = false;
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("habilidad")) > habilidadesEnNumeros.get(request.getHabilidades().get("habilidad")))
				add = false;
			if(habilidadesEnNumeros.get(habilidadesDelPartido.get("tactica")) > habilidadesEnNumeros.get(request.getHabilidades().get("tactica")))
				add = false;
			System.out.println(habilidadesEnNumeros.get(habilidadesDelPartido.get("estado")));
			System.out.println(habilidadesEnNumeros.get(request.getHabilidades().get("estado")));
			if (add)
				response.addPartido(p);
		}
		return response;
    }
	
	@RequestMapping(value = "/creacion", method = RequestMethod.POST)
    public CrearPartidoResponse crear(@RequestBody CrearPartidoRequest request) {
		CrearPartidoResponse response = new CrearPartidoResponse();
		partidoRepository.save(request.getPartido());
		
		response.setCodigoRespuesta(1);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/postularme", method = RequestMethod.POST)
    public PostularmePartidoResponse postularme(@RequestBody PostularmePartidoRequest request) {
		PostularmePartidoResponse response = new PostularmePartidoResponse();
		
		Postulacion postulacion = new Postulacion();
		postulacion.setIdJugador(request.getIdJugador());
		postulacion.setIdPartido(request.getIdPartido());
		
		postulacionRepository.save(postulacion);
		
		response.setCodigoRespuesta(10);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/postulaciones", method = RequestMethod.GET)
    public PostulacionesPartidoResponse postulaciones(@RequestParam String idPartido) {
		PostulacionesPartidoResponse response = new PostulacionesPartidoResponse();
		
		List<Postulacion> postulaciones = postulacionRepository.findByIdPartido(idPartido);
		
		response.setCodigoRespuesta(11);
		response.setMensaje("OK");
		response.setPostulaciones(postulaciones);
		
		return response;
    }
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public GetAllPartidosResponse getAll(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon, @RequestParam("idUsuario") String idUsuario) {
		GetAllPartidosResponse response = new GetAllPartidosResponse();
		List<Partido> partidos = partidoRepository.findAll();
		if (lat != null && lon != null) {
			partidos = getMatchDistance(lat, lon, partidos);
			partidos = orderMatchesByDateAndDistance(partidos);
		} else {
			//Por si falla el get de coordenadas
			partidos = getMatchDistance(-34.614362, -58.4234552, partidos);
		}
		
		for (Partido partido : partidos) {
			List<Postulacion> postulaciones = postulacionRepository.findByIdPartido(partido.getId());
			for (Postulacion postulacion : postulaciones) {
				if (postulacion.getIdJugador().equals(idUsuario)) {
					partido.setUsuarioPostulado(true);
					break;
				}
			}
			if (partido.isUsuarioPostulado()) {
				break;
			}
		}
		
		response.setPartidos(partidos);
        return response;
    }
	
	class PartidoDistancia{
		int id;
		int distancia;
		public PartidoDistancia(int id, int distancia) {
			this.id = id;
			this.distancia = distancia; 
		}
	}
	
	private List<Partido> orderMatchesByDateAndDistance(List<Partido> partidos) {
		partidos.sort(Comparator.comparing((Partido p) -> p.getDia()).thenComparing(p->p.getHorario()).thenComparing(p->p.getDistancia()));
		return partidos;
	}

	@RequestMapping(value = "/aceptar", method = RequestMethod.POST)
    public AceptarJugadorResponse postularme(@RequestBody AceptarJugadorRequest request) {
		AceptarJugadorResponse response = new AceptarJugadorResponse();
		
		Postulacion postulacion = postulacionRepository.findById(request.getIdPostulacion());
		
		Partido partido = partidoRepository.findById(postulacion.getIdPartido());
		partido.setIdUsuarioJugador(postulacion.getIdJugador());
		
		partidoRepository.save(partido);
		
		response.setCodigoRespuesta(12);
		response.setMensaje("OK");
		
		return response;
    }
	
	@RequestMapping(value = "/borrar", method = RequestMethod.POST)
    public BorrarPartidoResponse borrarPartido (@RequestBody BorrarPartidoRequest request) {
		BorrarPartidoResponse response = new BorrarPartidoResponse();
		
		Partido partido  = partidoRepository.findById(request.getIdPartido());
		
		partidoRepository.delete(partido);
		
		response.setCodigoRespuesta(12);
		response.setMensaje("OK");
		
		return response;
    }

	public List<Partido> getMatchDistance(double d, double e2, List<Partido> partidos) {
		// String apiKey = "AIzaSyBXuoucteWIPZ5I68RhZe-x-We2xktuGWs";
		String apiKey = "AIzaSyDDCuxOg63WU4nGq9Vdxi59WNv3pwui62g";
		String direccion = Double.toString(d) + ',' + Double.toString(e2);
		String destination = "";
		for (Partido p : partidos) {
			if (p.getDireccion() != null) {
				destination += p.getDireccion();
				destination += ", Buenos Aires, Ciudad Autónoma de Buenos Aires";
				destination += "|";
			}
		}
		destination = destination.replaceAll(" ", "+");
		destination.substring(0, destination.length() - 1);

		URL apiCall;
		String response = "";

		try {
			apiCall = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + direccion
					+ "&destinations=" + destination + "&language=es&key=" + apiKey);
			response = apiGet(apiCall);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		String value = "";
		String text = "";
		JSONObject jsonObj;
		JSONArray array;
		JSONObject jsonObj2;
		JSONArray array2 = null;
		
		try {
			jsonObj = new JSONObject(response);
			array = jsonObj.getJSONArray("rows");
			jsonObj2 = array.getJSONObject(0);
			array2 = jsonObj2.getJSONArray("elements");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		int i = 0;
		for (Partido p : partidos) {
			if (p.getDireccion() != null) {
				try {
					JSONObject distance = (JSONObject) ((JSONObject) array2.getJSONObject(i)).get("distance");
					value = ((JSONObject) distance).get("value").toString();
					text = ((JSONObject) distance).get("text").toString();
				} catch (Exception e) {
					value = "999999";
					text = "99999 km";
				}
				p.setDistancia(Double.valueOf(value));
				p.setDistanciaString(text);
				i++;
			}
		}
		return partidos;
	}

	public String apiGet(URL url) {
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			System.out.println("Sending get request : " + url);
			System.out.println("Response code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();

			System.out.println(response.toString());
			return response.toString();
		} catch (IOException e) {
			return "";
		}
	}

}
