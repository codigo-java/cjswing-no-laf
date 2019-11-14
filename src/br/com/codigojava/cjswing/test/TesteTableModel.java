package br.com.codigojava.cjswing.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import org.apache.commons.lang3.StringUtils;

import br.com.codigojava.cjswing.BooleanCellEditor;
import br.com.codigojava.cjswing.BooleanCellRender;
import br.com.codigojava.cjswing.CJTable;
import br.com.codigojava.cjswing.CJTableColumn;
import br.com.codigojava.cjswing.CJTableModel;
import br.com.codigojava.cjswing.ComboCellEditor;
import br.com.codigojava.cjswing.CurrencyCellRender;
import br.com.codigojava.cjswing.DateCellEditor;
import br.com.codigojava.cjswing.DateCellRender;
import br.com.codigojava.cjswing.HorizontalPosition;
import br.com.codigojava.cjswing.IntegerCellRender;

public class TesteTableModel extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private CJTable tableAlunos;
	private CJTable tablePessoas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteTableModel frame = new TesteTableModel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TesteTableModel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(1024, 450));
		setExtendedState(JFrame.NORMAL);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPanePessoas = new JScrollPane();
		scrollPanePessoas.setBounds(10, 10, 452, 300);
		contentPane.add(scrollPanePessoas);

		tablePessoas = new CJTable();
		scrollPanePessoas.setViewportView(tablePessoas);
		
		JTableHeader headerPessoas = tablePessoas.getTableHeader();
		headerPessoas.setBackground(Color.GREEN);

		CJTableColumn[] colunasPessoa = { 
				new CJTableColumn("Id", Long.class), 
				new CJTableColumn("Nome", String.class),
				new CJTableColumn("Data Nasc.", Date.class), 
				new CJTableColumn("Salário", BigDecimal.class) };

		List<Pessoa> pessoas = Arrays.asList(
				new Pessoa(1, "Fulano", new Date(), new BigDecimal(3050.0)),
				new Pessoa(2, "Ciclano", new Date(), new BigDecimal(5050.35)),
				new Pessoa(3, "Beltrano", new Date(), new BigDecimal(2500.90)));

		CJTableModel<Pessoa> modelPessoa = new CJTableModel<>(colunasPessoa, pessoas);
		// Aqui inoformamos que a classe Pessoa do modelPessoa implementa Serializable
		// Se comentar a linha abaixo o reflections vai obter o valor do atributo
		// serialVersionUID
		// e exibir na primeira coluna da table
		modelPessoa.isSeriazizable(true);

		tablePessoas.setModel(modelPessoa);
		tablePessoas.getColumnModel().getColumn(0).setCellRenderer(new IntegerCellRender(5, HorizontalPosition.CENTER));
		List<String> itens = Arrays.asList("Beltrano", "Fulano", "Clicano");
		modelPessoa.setEditableColumn(1, true);
		tablePessoas.getColumnModel().getColumn(1).setCellEditor(new ComboCellEditor(itens));
		tablePessoas.getColumnModel().getColumn(2).setCellRenderer(new DateCellRender());
		tablePessoas.getColumnModel().getColumn(2).setCellEditor(new DateCellEditor());
		tablePessoas.getColumnModel().getColumn(3).setCellRenderer(new CurrencyCellRender());

		JScrollPane scrollPaneAlunos = new JScrollPane();
		scrollPaneAlunos.setBounds(484, 10, 507, 300);
		contentPane.add(scrollPaneAlunos);

		tableAlunos = new CJTable();
		scrollPaneAlunos.setViewportView(tableAlunos);
		
		JTableHeader headerAlunos = tableAlunos.getTableHeader();
		headerAlunos.setBackground(Color.ORANGE);

		CJTableColumn[] colunasAluno = { 
				new CJTableColumn("Codigo", Integer.class),
				new CJTableColumn("Pessoa", Pessoa.class), 
				new CJTableColumn("RA", String.class),
				new CJTableColumn("Matriculado", Boolean.class) };

		CJTableModel<Aluno> modelAluno = new CJTableModel<>(colunasAluno);
		modelAluno.setEditableColumn(3, true);
		tableAlunos.setModel(modelAluno);
		tableAlunos.getColumnModel().getColumn(0).setCellRenderer(new IntegerCellRender(5, HorizontalPosition.CENTER));
		tableAlunos.getColumnModel().getColumn(2).setCellRenderer(new IntegerCellRender(10, HorizontalPosition.CENTER));
		tableAlunos.getColumnModel().getColumn(3).setCellRenderer(new BooleanCellRender());
		tableAlunos.getColumnModel().getColumn(3).setCellEditor(new BooleanCellEditor());

		JButton btnAddTodos = new JButton("Add Todos");
		btnAddTodos.addActionListener(e -> modelAluno.addListObject(createAlunos(pessoas)));
		btnAddTodos.setBounds(136, 323, 112, 33);
		contentPane.add(btnAddTodos);

		JButton btnAdd = new JButton("Adiciona");
		btnAdd.addActionListener(e -> modelAluno.addObject( new Aluno(modelPessoa.getObject(tablePessoas.getSelectedRow()).getId(),
																	  modelPessoa.getObject(tablePessoas.getSelectedRow()),
																	  String.valueOf(new Random().nextInt(10000000)), 
																	  true))
		);
		
		btnAdd.setBounds(12, 323, 112, 33);
		contentPane.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(e -> modelAluno.removeObject(tableAlunos.getSelectedRow()));
		btnRemove.setBounds(484, 323, 112, 33);
		contentPane.add(btnRemove);

		JButton btnRemoveTodos = new JButton("Remove Todos");
		btnRemoveTodos.setBounds(608, 323, 130, 33);
		btnRemoveTodos.addActionListener(e -> modelAluno.clear());
		contentPane.add(btnRemoveTodos);
	}
	
	private List<Aluno> createAlunos(List<Pessoa> pessoas) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		for (Pessoa pessoa : pessoas) {
			alunos.add(new Aluno(pessoa.getId(), pessoa, StringUtils.leftPad(String.valueOf(new Random().nextInt(10000000)), 10, "0"), true));
		}
		return alunos;
	}
}


class Aluno {

	private Integer codigo;
	private Pessoa pessoa;
	private String ra;
	private Boolean matriculado;

	public Aluno(Integer codigo, Pessoa pessoa, String ra, Boolean matriculado) {
		super();
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.ra = ra;
		this.matriculado = matriculado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public Boolean getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(Boolean matriculado) {
		this.matriculado = matriculado;
	}
}

class Pessoa implements Serializable {

	private static final long serialVersionUID = -3634585407361858884L;

	private Integer id;
	private String nome;
	private Date data;
	private BigDecimal salario;

	public Pessoa(Integer id, String nome, Date data, BigDecimal salario) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.salario = salario;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
