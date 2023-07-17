prod: manifests/
	@for file in $(wildcard manifests/*); do \
        echo "Deploy de $$file"; \
        sudo kubectl apply -f $$file; \
    done