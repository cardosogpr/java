package io.altar.jseproject.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger; // Se estiver a usar o Logger noutro local, caso contrário, pode remover

import io.altar.jseproject.model.Entity; // Certifique-se que o caminho está correto

public abstract class EntityRepository<T extends Entity> {

    private Map<Long, T> db = new HashMap<>(); 
    private Long currentId = 1L; 

    // Este método apenas calcula o próximo ID, não o atribui ou incrementa globalmente
    private Long getNextCurrentId() { 
        return currentId + 1;
    }

    public Long addEntity(T e) { 
        // Verifica se a entidade já tem um ID atribuído antes de adicionar.
        // Isto é um mecanismo de segurança para evitar re-adição acidental.
        if (e.getEntityId() != null && db.containsKey(e.getEntityId())) {
            System.err.println("Aviso: Entidade com ID " + e.getEntityId() + " já existe. Use 'edit' para atualizar.");
            return e.getEntityId(); // Retorna o ID existente se já estiver no DB
        }

        e.setEntityId(currentId); // Atribui o ID corrente à entidade
        db.put(currentId, e);     // Adiciona a entidade ao mapa com o ID gerado
        currentId++;              // Incrementa o gerador de IDs para a próxima entidade
        System.out.println("Entidade " + e.getClass().getSimpleName() + " adicionada com ID: " + e.getEntityId());
        return e.getEntityId();
    }
    
    // CORREÇÃO AQUI: Agora retorna a entidade com base no ID fornecido
    public T getById(long id) { // Renomeado o parâmetro para 'id' para evitar confusão com a variável de classe 'currentId'
        T entity = db.get(id);
        if (entity == null) {
            System.out.println("Entidade com ID " + id + " não encontrada.");
        }
        return entity;
    }

    public void edit(T entity) {    
        if (entity != null && entity.getEntityId() != null) {
            // Verifica se a entidade realmente existe antes de tentar editá-la
            if (db.containsKey(entity.getEntityId())) {
                db.put(entity.getEntityId(), entity); 
                System.out.println("Entidade com ID " + entity.getEntityId() + " atualizada.");
            } else {
                System.err.println("Erro: Não foi possível editar. Entidade com ID " + entity.getEntityId() + " não encontrada no repositório.");
            }
        } else {
            System.err.println("Erro: Entidade ou ID nulo(a) para edição.");
        }
    }
    
    public void remove(Long id) { 
        if (id != null && db.containsKey(id)) {
            db.remove(id); 
            System.out.println("Entidade com ID " + id + " removida.");
        } else {
            System.err.println("Erro: ID nulo ou entidade não encontrada para remoção.");
        }
        
    }
    


    // Opcional: Adicionar um método para obter todas as entidades
    public Map<Long, T> getAll() {
        return new HashMap<>(db); // Retorna uma cópia para evitar modificações externas diretas
    }
    
}