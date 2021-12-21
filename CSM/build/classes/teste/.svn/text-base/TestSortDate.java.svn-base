package teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.alcatellucent.csm.beans.PassHistory;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class TestSortDate {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		class Cliente {
			
			private String nome;
			private String endereco;
			private Calendar data;
			
			@SuppressWarnings("unused")
			public String getNome() {
				return nome;
			}
			@SuppressWarnings("unused")
			public void setNome(String nome) {
				this.nome = nome;
			}
			@SuppressWarnings("unused")
			public String getEndereco() {
				return endereco;
			}
			@SuppressWarnings("unused")
			public void setEndereco(String endereco) {
				this.endereco = endereco;
			}
			@SuppressWarnings("unused")
			public Calendar getData() {
				return data;
			}
			@SuppressWarnings("unused")
			public void setData(Calendar data) {
				this.data = data;
			}
		}
		
		
		Collection<Cliente> cCliente = new ArrayList<Cliente>();
		
		Cliente cliente = new Cliente();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cliente.setData(cal);
		cliente.setEndereco("teste1");
		cliente.setNome("Igor1");
		cCliente.add(cliente);
		
		cliente = new Cliente();
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -5);		
		cliente.setData(cal);
		cliente.setEndereco("teste2");
		cliente.setNome("Igor2");
		cCliente.add(cliente);
		
		
		cliente = new Cliente();
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -11);
		cliente.setData(cal);
		cliente.setEndereco("teste3");
		cliente.setNome("Igor3");
		cCliente.add(cliente);
		
		Collections.sort((List<Cliente>) cCliente, new Comparator() {
			
			public int compare(Object o1, Object o2) {
				Cliente c1 = (Cliente) o1;
				Cliente c2 = (Cliente) o2;
				
				return (c1.getData().compareTo(c2.getData()) * (-1));
			
			}
		});
		
		for (Cliente c : cCliente) {
			
			System.out.println(c.data.getTime() + " | " + c.getNome() + " | " + c.getEndereco());
			
		}

		System.exit(0);

	}

}
