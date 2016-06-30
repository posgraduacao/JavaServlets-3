package up.jservlets.bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import up.jservlets.Pessoa;

/**
 * Servlet implementation class AdicionaContato
 */
@WebServlet("/AdicionaContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AdicionaContato() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		Pessoa pessoa;
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");

		try {
			if (email != null) {
				pessoa = (Pessoa) deserializaObjeto("C:\\temp\\" + email);
				System.out.println("Contato carregado com sucesso");
				pessoa = (Pessoa) deserializaObjeto("C:\\temp\\" + email);
				System.out.println("Contato carregado com sucesso");
				out.println("<br>Nome: " + pessoa.getNome());
				out.println("<br>Endereço: " + pessoa.getEndereco());
				out.println("<br>Email: " + pessoa.getEmail());
				out.println("<br>Nascimento " + pessoa.getDataNascimento());
			} else {
				out.println(
						"Parametro inválido, execute <URL servlet>?email=<nome do email para carregar informações>");
			}
		} catch (Exception e) {
			out.println("Contato não encontrado");
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		// pegando os parâmetros do request
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataNascimento = request.getParameter("dataNascimento");

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setEndereco(endereco);
		pessoa.setEmail(email);
		pessoa.setDataNascimento(dataNascimento);

		out.println("<html>");
		out.println("<body>");

		try {
			serializaObjeto("C:\\temp\\" + email, pessoa);
			out.println("Contato " + pessoa.getNome() + " adicionado com sucesso");
		} catch (Exception e) {
			out.println("Problemas no adicionamento do contato, contate o administrador");
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");

	}

	public void serializaObjeto(String path, Object obj) throws Exception {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}

	public Object deserializaObjeto(String path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object result = ois.readObject();
		ois.close();

		return result;
	}

}
